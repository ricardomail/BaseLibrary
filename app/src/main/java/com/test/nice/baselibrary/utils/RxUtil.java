package com.test.nice.baselibrary.utils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
}
