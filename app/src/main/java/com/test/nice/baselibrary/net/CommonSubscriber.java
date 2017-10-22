package com.test.nice.baselibrary.net;

import android.text.TextUtils;

import com.test.nice.baselibrary.net.exception.ApiException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by xingge on 2017/10/22.
 */

public abstract class CommonSubscriber extends ResourceSubscriber{
	private String mErrorMsg;
	protected CommonSubscriber(){

	}

	protected CommonSubscriber(String errorMsg){
		this.mErrorMsg = errorMsg;
	}

	@Override
	public void onError(Throwable e) {
		if(mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)){
			_onError(mErrorMsg);
		}else if(e instanceof ApiException){
			_onError(((ApiException) e).toString());
		}else if(e instanceof HttpException){
			_onError("数据加载失败");
		}else{
			_onError("未知错误");
		}
	}

	@Override
	public void onComplete() {

	}

	protected abstract void _onError(String mErrorMsg);
}
