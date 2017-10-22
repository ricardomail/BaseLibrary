package com.test.nice.baselibrary.mvpbase;

/**
 * BaseActivity class
 *
 * @author CJX
 * @date 2017/10/20
 */

public interface IBaseView {
    /**
     * 显示错误信息
     * @param error
     */
    void showError(String error);

    /**
     * 错误状态
     */
    void stateError();

    /**
     * 空状态
     */
    void stateEmpty();

    /**
     * loading状态
     */
    void stateLoading();

}
