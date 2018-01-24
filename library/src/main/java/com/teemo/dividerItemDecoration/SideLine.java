package com.teemo.dividerItemDecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;


public class SideLine {

    boolean isHave = true;
    /**
     * A single color value in the form 0xAARRGGBB.
     **/
    int color;
    Drawable drawable;
    int drawableIcon;
    /**
     * 单位dp
     */
    float widthDp;
    /**
     * startPaddingDp,分割线起始的padding，水平方向左为start，垂直方向上为start
     * endPaddingDp,分割线尾部的padding，水平方向右为end，垂直方向下为end
     */
    float startPaddingDp;
    float endPaddingDp;

    public SideLine(float widthDp) {
        this.widthDp = widthDp;
    }

    public SideLine() {
    }

    public SideLine setHave(boolean have) {
        isHave = have;
        return this;
    }

    public SideLine setWidthDp(float widthDp) {
        this.widthDp = widthDp;
        return this;
    }

    public SideLine setStartPaddingDp(float startPaddingDp) {
        this.startPaddingDp = startPaddingDp;
        return this;
    }

    public SideLine setEndPaddingDp(float endPaddingDp) {
        this.endPaddingDp = endPaddingDp;
        return this;
    }

    public SideLine setDrawable(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public SideLine setColor(int color) {
        this.color = color;
        return this;
    }

    public SideLine setDrawable(@DrawableRes int iconRes) {
        this.drawableIcon = iconRes;
        return this;
    }
}