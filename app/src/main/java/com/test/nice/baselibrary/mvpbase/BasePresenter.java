package com.test.nice.baselibrary.mvpbase;

import io.reactivex.disposables.Disposable;

/**
 * BaseActivity class
 *
 * @author CJX
 * @date 2017/10/20
 */

public interface BasePresenter {
    /**
     * 解除订阅rxjava
     */
    void unsubcrible();

}
