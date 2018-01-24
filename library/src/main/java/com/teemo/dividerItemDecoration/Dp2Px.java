package com.teemo.dividerItemDecoration;

import android.content.Context;
import android.util.TypedValue;

/**
 * ���õ�λת���ĸ�����
 *
 * @author zhy
 */
public class Dp2Px {
    private Dp2Px() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dpתpx
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int convert(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }


}