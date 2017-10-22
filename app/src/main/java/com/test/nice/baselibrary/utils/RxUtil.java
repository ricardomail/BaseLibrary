package com.test.nice.baselibrary.utils;

import com.test.nice.baselibrary.net.HttpResponse;
import com.test.nice.baselibrary.net.exception.ApiException;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xingge on 2017/10/21.
 */

public class RxUtil {
	/**
	 * 同一线程处理
	 * @param <T>
	 * @return
	 */
	public static <T>FlowableTransformer<T, T> rxSchedulerHelper(){
		return new FlowableTransformer<T, T>() {
			@Override
			public Publisher<T> apply(@NonNull Flowable<T> upstream) {
				return upstream.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread());
			}
		};
	}

	public static <T> FlowableTransformer<HttpResponse<T>, T> handleResult(){
		return new FlowableTransformer<HttpResponse<T>, T>() {
			@Override
			public Publisher<T> apply(@NonNull Flowable<HttpResponse<T>> upstream) {
				return upstream.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {

					@Override
					public Flowable<T> apply(@NonNull HttpResponse<T> tHttpResponse) throws Exception {
						if(tHttpResponse.isSuccess()){
							return createData(tHttpResponse.getData());
						}else{
							return Flowable.error(new ApiException("服务器异常"));
						}
					}
				});
			}
		};
	}

	/**
	 * 生成Flowable
	 * @param <T>
	 * @return
	 */
	public static <T> Flowable<T> createData(final T t) {
		return Flowable.create(new FlowableOnSubscribe<T>() {
			@Override
			public void subscribe(FlowableEmitter<T> emitter) throws Exception {
				try {
					emitter.onNext(t);
					emitter.onComplete();
				} catch (Exception e) {
					emitter.onError(e);
				}
			}
		}, BackpressureStrategy.BUFFER);
	}
}
