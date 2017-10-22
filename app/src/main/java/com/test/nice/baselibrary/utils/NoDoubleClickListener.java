package com.test.nice.baselibrary.utils;

import android.icu.util.Calendar;
import android.view.View;

/**
 * Created by ${Nice} on 2017/9/25.
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_TIME = 1000;
    private long lastClickTime = 0;

    public abstract void onNoDoubleClick(View v);

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(view);
        }
    }
}
