package com.test.nice.baselibrary.mvpbase;

import com.test.nice.baselibrary.base.BaseActivity;



/**
 * BaseMvpActivity class
 *
 * @author CJX
 * @date 2017/10/20
 */
public abstract class BaseMvpActivity<V, T extends BasePresenterImpl> extends BaseActivity{
    public T presenter;

    /**
     * 初始化presenter
     * @return
     */
    protected abstract BasePresenterImpl initPresenter();

    @Override
    protected void onDestroy() {
        if(presenter != null){
            presenter.unsubcrible();
            presenter.dettach();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null){
            presenter.attach((V)this);
        }
    }
}
