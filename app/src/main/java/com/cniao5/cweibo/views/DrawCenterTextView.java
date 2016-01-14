package com.cniao5.cweibo.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：8:02 PM
 * Remark：
 */
public class DrawCenterTextView extends TextView {
    public DrawCenterTextView(Context context) {
        super(context);
    }

    public DrawCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawCenterTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas) {
        Drawable drawables[] = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (null != drawableLeft) {
            setGravity(Gravity.START);
            int drawWidth = drawableLeft.getIntrinsicWidth();
            int drawablePadding= getCompoundDrawablePadding();
            int textWidth = (int) getPaint().measureText(getText().toString());
            int bodyWidth = drawWidth + textWidth+drawablePadding;
            canvas.translate((getWidth() - bodyWidth)/ 2, 0);
        }
        Drawable drawableRight = drawables[2];
        if (null != drawableRight) {
            setGravity(Gravity.END);
            int drawWidth = drawableRight.getIntrinsicWidth();
            int drawablePadding= getCompoundDrawablePadding();
            int textWidth = (int) getPaint().measureText(getText().toString());
            int bodyWidth = drawWidth + textWidth+drawablePadding;
            canvas.translate(-(getWidth() - bodyWidth)/ 2, 0);
        }
        super.onDraw(canvas);
    }
}
