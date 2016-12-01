package com.example.gtx660ti.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by GTX660TI on 01.12.2016.
 */

public class Utils {
    public static float convertDPtoPX(Context context, int dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
