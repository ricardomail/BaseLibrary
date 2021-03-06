package com.test.nice.baselibrary.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ${Nice} on 2017/9/25.
 */

public class JsonAssetsReaderUtil {
    public static String getJsonStrFromAssets(Context context, String jsonFileName) {
        InputStreamReader inputStreamReader = null;
        StringBuilder stringBuilder = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open(jsonFileName), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String jsonStr;
            stringBuilder = new StringBuilder();
            while ((jsonStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }


}
