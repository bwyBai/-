package com.example.mvpdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.tamsiree.rxkit.RxImageTool;

import java.text.DecimalFormat;


public class WeightBarView extends View {

    //第一段颜色画笔
    private Paint firstPaint;
    //    第二段颜色画笔
    private Paint secondPaint;
    //    第三段颜色画笔
    private Paint threePaint;
    //    第四段颜色画笔
    private Paint forePaint;
    //    原点画笔
    private Paint circlePaint;
    private Paint circleWhitPaint;

    //标准画笔
    private Paint standardPaint;
    private Paint standardNumPaint;
    //偏轻画笔
    private Paint underPaint;
    private Paint underNumPaint;
    //偏重画笔
    private Paint weightingPaint;
    private Paint overNumPaint;
    //过重画笔
    private Paint overPaint;

    //当前View的宽度
    private int width;

    //当前View的高度

    private int left;
    private int right;
    private int top;
    private int bottom;

    private int viewWidth;

    private float standardWeight;
    private float underWeight;
    private float overrWeight;
    private float currentWeight;
    private float minValue = 0;
    private float maxValue = 100;
    private DecimalFormat decimalFormat;

    public WeightBarView(Context context) {
        super(context);

        init();
    }

    public WeightBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        decimalFormat = new DecimalFormat("##.##");

        firstPaint = new Paint();
        firstPaint.setColor(getResources().getColor(R.color.healthy_first_weight));
        firstPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        firstPaint.setAntiAlias(true);

        secondPaint = new Paint();
        secondPaint.setColor(getResources().getColor(R.color.healthy_second_weight));
        secondPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        secondPaint.setAntiAlias(true);

        threePaint = new Paint();
        threePaint.setColor(getResources().getColor(R.color.healthy_three_weight));
        threePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        threePaint.setAntiAlias(true);

        forePaint = new Paint();
        forePaint.setColor(getResources().getColor(R.color.healthy_fore_weight));
        forePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        forePaint.setAntiAlias(true);

        circlePaint = new Paint();
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(RxImageTool.dip2px(2));
        circlePaint.setAntiAlias(true);
        circleWhitPaint = new Paint();
        circleWhitPaint.setColor(getResources().getColor(R.color.white));
        circleWhitPaint.setStyle(Paint.Style.FILL);
        circleWhitPaint.setAntiAlias(true);

        standardPaint = new Paint();
        standardPaint.setTextSize(RxImageTool.sp2px(14));
        standardPaint.setColor(getResources().getColor(R.color.healthy_second_weight));
        standardPaint.setAntiAlias(true);

        underPaint = new Paint();
        underPaint.setTextSize(RxImageTool.sp2px(14));
        underPaint.setColor(getResources().getColor(R.color.healthy_first_weight));
        underPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        underPaint.setAntiAlias(true);

        weightingPaint = new Paint();
        weightingPaint.setTextSize(RxImageTool.sp2px(14));
        weightingPaint.setColor(getResources().getColor(R.color.healthy_three_weight));
        weightingPaint.setAntiAlias(true);

        overPaint = new Paint();
        overPaint.setTextSize(RxImageTool.sp2px(14));
        overPaint.setColor(getResources().getColor(R.color.healthy_fore_weight));
        overPaint.setAntiAlias(true);

        standardNumPaint = new Paint();
        standardNumPaint.setTextSize(RxImageTool.sp2px(10));
        standardNumPaint.setColor(getResources().getColor(R.color.color_tab_text));
        standardNumPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        standardNumPaint.setAntiAlias(true);

        underNumPaint = new Paint();
        underNumPaint.setTextSize(RxImageTool.sp2px(10));
        underNumPaint.setColor(getResources().getColor(R.color.color_tab_text));
        underNumPaint.setAntiAlias(true);

        overNumPaint = new Paint();
        overNumPaint.setTextSize(RxImageTool.sp2px(10));
        overNumPaint.setColor(getResources().getColor(R.color.color_tab_text));
        overNumPaint.setAntiAlias(true);


        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                top = getTop();
                bottom = getBottom();
                right = getRight();
                left = getLeft();
                width = right - left;
                viewWidth = width / 4;
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float mMinHeight = RxImageTool.dp2px(50);
        if (heightMode == MeasureSpec.EXACTLY) {
            heightSize = heightSize >= mMinHeight ? heightSize
                    : (int) mMinHeight;
        }

        if (heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = heightSize >= mMinHeight ? heightSize
                    : (int) mMinHeight;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = heightSize >= mMinHeight ? heightSize
                    : (int) mMinHeight;
        }
        int minHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,
                heightMode);
        setMeasuredDimension(widthMeasureSpec, minHeightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(decimalFormat.format(underWeight), left + viewWidth - 20, top + RxImageTool.dp2px(10), underNumPaint);
        canvas.drawText(decimalFormat.format(standardWeight), left + (viewWidth * 2) - 20, top + RxImageTool.dp2px(10), standardNumPaint);
        canvas.drawText(decimalFormat.format(overrWeight), left + (viewWidth * 3) - 20, top + RxImageTool.dp2px(10), overNumPaint);


        RectF rectF = new RectF(left, top + RxImageTool.dp2px(20), left + viewWidth, top + RxImageTool.dp2px(26));
        canvas.drawRoundRect(rectF, 10, 10, firstPaint);
        canvas.drawRect(left + viewWidth - 5, top + RxImageTool.dp2px(20), left + (viewWidth * 2), top + RxImageTool.dp2px(26), secondPaint);
        RectF foreRect = new RectF(left + (viewWidth * 3), top + RxImageTool.dp2px(20), right - 5, top + RxImageTool.dp2px(26));
        canvas.drawRoundRect(foreRect, 10, 10, forePaint);
        canvas.drawRect(left + (viewWidth * 2), top + RxImageTool.dp2px(20), left + (viewWidth * 3) + 5, top + RxImageTool.dp2px(26), threePaint);


        canvas.drawText("偏轻", left, top + RxImageTool.dp2px(45), underPaint);
        canvas.drawText("标准", left + viewWidth - 5, top + RxImageTool.dp2px(45), standardPaint);
        canvas.drawText("偏重", left + (viewWidth * 2), top + RxImageTool.dp2px(45), weightingPaint);
        canvas.drawText("超重", left + (viewWidth * 3), top + RxImageTool.dp2px(45), overPaint);
        if (0 != currentWeight && 0 != standardWeight) {
            if (currentWeight < underWeight) {
                //偏轻
                circlePaint.setColor(getResources().getColor(R.color.healthy_first_weight));
            } else if (currentWeight >= +underWeight && currentWeight <= standardWeight) {
                //标准
                circlePaint.setColor(getResources().getColor(R.color.healthy_second_weight));
            } else if (currentWeight > standardWeight && currentWeight <= overrWeight) {
                //偏重
                circlePaint.setColor(getResources().getColor(R.color.healthy_three_weight));
            } else if (currentWeight > overrWeight) {
                //超重
                circlePaint.setColor(getResources().getColor(R.color.healthy_fore_weight));
            }
            if (currentWeight < minValue) {
                RectF circleF = new RectF(left + RxImageTool.dp2px(1), top + RxImageTool.dp2px(16), left + RxImageTool.dp2px(15), top + RxImageTool.dp2px(30));
                canvas.drawOval(circleF, circleWhitPaint);
                canvas.drawOval(circleF, circlePaint);
            } else if (currentWeight > maxValue) {
                RectF circleF = new RectF(left + (viewWidth * 4) - RxImageTool.dp2px(14), top + RxImageTool.dp2px(16), left + (viewWidth * 4), top + RxImageTool.dp2px(30));
                canvas.drawOval(circleF, circleWhitPaint);
                canvas.drawOval(circleF, circlePaint);
            } else {
                float v = (currentWeight - minValue) / (maxValue - minValue);
                float currentProgress = (left + (viewWidth * 4)) * v;
                RectF circleF = new RectF(currentProgress - RxImageTool.dp2px(7), top + RxImageTool.dp2px(16), currentProgress + RxImageTool.dp2px(7), top + RxImageTool.dp2px(30));
                canvas.drawOval(circleF, circleWhitPaint);
                canvas.drawOval(circleF, circlePaint);
            }

        } else {
            circlePaint.setColor(getResources().getColor(R.color.healthy_first_weight));
            RectF circleF = new RectF(left + RxImageTool.dp2px(1), top + RxImageTool.dp2px(16), left + RxImageTool.dp2px(15), top + RxImageTool.dp2px(30));
            canvas.drawOval(circleF, circleWhitPaint);
            canvas.drawOval(circleF, circlePaint);
        }


    }


    public void setCurrentWeight(float standardWeight, float currentWeight) {
        this.standardWeight = standardWeight;
        this.currentWeight = currentWeight;
        underWeight = standardWeight - (standardWeight * 0.1f);
        minValue = standardWeight - (standardWeight * 0.2f);
        overrWeight = standardWeight + (standardWeight * 0.1f);
        maxValue = standardWeight + (standardWeight * 0.2f);
        invalidate();
    }
}
