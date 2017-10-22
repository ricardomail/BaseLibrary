package com.test.nice.baselibrary.mvpbase;

import com.test.nice.baselibrary.base.BaseFragment;

/**
 * BaseMvpFragment class
 *
 * @author CJX
 * @date 2017/10/20
 */
public abstract class BaseMvpFragment<V, T extends BasePresenterImpl> extends BaseFragment {
    private T prestener;

    /**
     * 初始化presenter
     */
    protected abstract void initPresenter();

    @Override
    public void onResume() {
        super.onResume();
        if(prestener != null){
            prestener.attach((V)this);
        }
    }

    @Override
    public void onDestroy() {
        if (prestener != null){
            prestener.dettach();
        }
        super.onDestroy();
    }
}
