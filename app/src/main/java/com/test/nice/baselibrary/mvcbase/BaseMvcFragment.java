package com.test.nice.baselibrary.mvcbase;

import com.test.nice.baselibrary.base.BaseFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BaseMvcFragment class
 *
 * @author CJX
 * @date 2017/10/20
 */
public abstract class BaseMvcFragment extends BaseFragment{
    protected CompositeDisposable mCompositeDisposable;

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

    @Override
    public void onDestroy() {
        unsubcrible();
        super.onDestroy();
    }
}
