package com.teemo.dividerItemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    private Context context;

    public DividerItemDecoration(Context context) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //left, top, right, bottom
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int itemPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();

            Divider divider = getDivider(itemPosition);

            if (divider != null) {
                SideLine line;
                if ((line = divider.getLeftSideLine()) != null && line.isHave) {
                    int lineWidthPx = Dp2Px.convert(context, line.widthDp);
                    int startPaddingPx = Dp2Px.convert(context, line.startPaddingDp);
                    int endPaddingPx = Dp2Px.convert(context, line.endPaddingDp);
                    if (line.drawable == null && line.drawableIcon != 0) {
                        line.drawable = ResourcesCompat.getDrawable(context.getResources(), line.drawableIcon, context.getTheme());
                    }
                    drawChildLeftVertical(child, c, parent, line.color, line.drawable, lineWidthPx, startPaddingPx, endPaddingPx);
                }
                if ((line = divider.getTopSideLine()) != null && line.isHave) {
                    int lineWidthPx = Dp2Px.convert(context, line.widthDp);
                    int startPaddingPx = Dp2Px.convert(context, line.startPaddingDp);
                    int endPaddingPx = Dp2Px.convert(context, line.endPaddingDp);
                    if (line.drawable == null && line.drawableIcon != 0) {
                        line.drawable = ResourcesCompat.getDrawable(context.getResources(), line.drawableIcon, context.getTheme());
                    }
                    drawChildTopHorizontal(child, c, parent, line.color, line.drawable, lineWidthPx, startPaddingPx, endPaddingPx);
                }
                if ((line = divider.getRightSideLine()) != null && line.isHave) {
                    int lineWidthPx = Dp2Px.convert(context, divider.getRightSideLine().widthDp);
                    int startPaddingPx = Dp2Px.convert(context, divider.getRightSideLine().startPaddingDp);
                    int endPaddingPx = Dp2Px.convert(context, divider.getRightSideLine().endPaddingDp);
                    if (line.drawable == null && line.drawableIcon != 0) {
                        line.drawable = ResourcesCompat.getDrawable(context.getResources(), line.drawableIcon, context.getTheme());
                    }
                    drawChildRightVertical(child, c, parent, line.color, line.drawable, lineWidthPx, startPaddingPx, endPaddingPx);
                }
                if ((line = divider.getBottomSideLine()) != null && line.isHave) {
                    int lineWidthPx = Dp2Px.convert(context, divider.getBottomSideLine().widthDp);
                    int startPaddingPx = Dp2Px.convert(context, divider.getBottomSideLine().startPaddingDp);
                    int endPaddingPx = Dp2Px.convert(context, divider.getBottomSideLine().endPaddingDp);
                    if (line.drawable == null && line.drawableIcon != 0) {
                        line.drawable = ResourcesCompat.getDrawable(context.getResources(), line.drawableIcon, context.getTheme());
                    }
                    drawChildBottomHorizontal(child, c, parent, line.color, line.drawable, lineWidthPx, startPaddingPx, endPaddingPx);
                }
            }
        }
    }

    private void drawChildBottomHorizontal(View child, Canvas c, RecyclerView parent, @ColorInt int color, @Nullable Drawable drawable, int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        int leftPadding;
        int rightPadding;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }

        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + lineWidthPx;
        if (drawable == null) {
            mPaint.setColor(color);

            c.drawRect(left, top, right, bottom, mPaint);
        } else {
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }

    }

    private void drawChildTopHorizontal(View child, Canvas c, RecyclerView parent, @ColorInt int color, @Nullable Drawable drawable, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding;
        int rightPadding;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int bottom = child.getTop() - params.topMargin;
        int top = bottom - lineWidthPx;
        if (drawable == null) {
            mPaint.setColor(color);

            c.drawRect(left, top, right, bottom, mPaint);
        } else {
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }

    }

    private void drawChildLeftVertical(View child, Canvas c, RecyclerView parent, @ColorInt int color, @Nullable Drawable drawable, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int topPadding;
        int bottomPadding;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int right = child.getLeft() - params.leftMargin;
        int left = right - lineWidthPx;
        if (drawable == null) {
            mPaint.setColor(color);

            c.drawRect(left, top, right, bottom, mPaint);
        } else {
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }

    }

    private void drawChildRightVertical(View child, Canvas c, RecyclerView parent, @ColorInt int color, @Nullable Drawable drawable, int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        int topPadding;
        int bottomPadding;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int left = child.getRight() + params.rightMargin;
        int right = left + lineWidthPx;
        if (drawable == null) {
            mPaint.setColor(color);

            c.drawRect(left, top, right, bottom, mPaint);
        } else {
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //outRect 看源码可知这里只是把Rect类型的outRect作为一个封装了left,right,top,bottom的数据结构,
        //作为传递left,right,top,bottom的偏移值来用的

        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        Divider divider = getDivider(itemPosition);

        if (divider == null) {
//            divider = new Y_DividerBuilder().create();
            outRect.set(0, 0, 0, 0);
        } else {
            SideLine line;
            int left = (line = divider.getLeftSideLine()) != null && line.isHave ? Dp2Px.convert(context, line.widthDp) : 0;
            int top = (line = divider.getTopSideLine()) != null && line.isHave ? Dp2Px.convert(context, line.widthDp) : 0;
            int right = (line = divider.getRightSideLine()) != null && line.isHave ? Dp2Px.convert(context, line.widthDp) : 0;
            int bottom = (line = divider.getBottomSideLine()) != null && line.isHave ? Dp2Px.convert(context, line.widthDp) : 0;

            outRect.set(left, top, right, bottom);
        }
    }


    public abstract @Nullable
    Divider getDivider(int itemPosition);


}
















