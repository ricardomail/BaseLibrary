package com.test.nice.baselibrary.utils.loadutil;


import com.test.nice.baselibrary.R;
import com.test.nice.baselibrary.utils.loadutil.callback.Callback;

/**
 * LoadingCallback class
 *
 * @author CJX
 * @date 2017/10/26
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }
}
