package com.test.nice.baselibrary.mvpbase;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BasePresenterImpl class
 *
 * @author CJX
 * @date 2017/10/20
 */
public class BasePresenterImpl<T> implements BasePresenter{
    public T mView;
    private Context mContext;
    protected CompositeDisposable mCompositeDisposable;

    public BasePresenterImpl(Context context){}

    protected void attach(T mView){
        this.mView = mView;
    }

    protected void dettach(){
        mView = null;
    }

    @Override
    public void unsubcrible() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubcrible(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }


}
