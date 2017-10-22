package com.test.nice.baselibrary.mvcbase;

import com.test.nice.baselibrary.base.BaseActivity;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BaseMvcActivity class
 *
 * @author CJX
 * @date 2017/10/20
 */
public abstract class BaseMvcActivity extends BaseActivity{
    protected CompositeDisposable mCompositeDisposable;

    public void unsubcrible() {
        if (mCompositeDisposable != null) {
            /**
             * 切断接受
             */
            mCompositeDisposable.clear();
        }
    }

    protected void addSubcrible(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    protected void onDestroy() {
        unsubcrible();
        super.onDestroy();
    }
}
