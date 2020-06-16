package com.example.mvpdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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
    private int height;

    private int left;
    private int right;

    //距离左边的内边距
    private int paddingLeft;

    //距离右边的内边距
    private int paddingRight;

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
//        firstPaint.setColor(Color.rgb(167, 228, 255));
        firstPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        firstPaint.setAntiAlias(true);

        secondPaint = new Paint();
        secondPaint.setColor(getResources().getColor(R.color.healthy_second_weight));
//        secondPaint.setColor(Color.rgb(91, 200, 247));
        secondPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        secondPaint.setAntiAlias(true);

        threePaint = new Paint();
        threePaint.setColor(getResources().getColor(R.color.healthy_three_weight));
//        threePaint.setColor(Color.rgb(248, 215, 119));
        threePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        threePaint.setAntiAlias(true);

        forePaint = new Paint();
        forePaint.setColor(getResources().getColor(R.color.healthy_fore_weight));
//        forePaint.setColor(Color.rgb(239, 142, 56));
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

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();//view的宽度
        height = getHeight();//view的高度
        paddingLeft = getPaddingLeft();//距离左边的距离
        paddingRight = getPaddingRight();//距离右边的距离
        //最大进度长度等于View的宽度-(左边的内边距+右边的内边距)
        viewWidth = RxImageTool.dip2px(75);
//        left = getLeft();
//        right = getRight();
//        width = right - left;//view的宽度
//        height = getTop();//view的高度
//        paddingLeft = getPaddingLeft();//距离左边的距离
//        paddingRight = getPaddingRight();//距离右边的距离
//        //最大进度长度等于View的宽度-(左边的内边距+右边的内边距)
//        viewWidth = RxImageTool.dip2px(75);

//
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("WeightBarView", "width:" + width);
        Log.e("WeightBarView", "height:" + height);
        Log.e("WeightBarView", "paddingLeft:" + paddingLeft);
        Log.e("WeightBarView", "paddingRight:" + paddingRight);

        canvas.drawText(decimalFormat.format(underWeight), width - paddingLeft + viewWidth - 20, height + RxImageTool.dp2px(10), underNumPaint);
        canvas.drawText(decimalFormat.format(standardWeight), width - paddingLeft + (viewWidth * 2) - 20, height + RxImageTool.dp2px(10), standardNumPaint);
        canvas.drawText(decimalFormat.format(overrWeight), width - paddingLeft + (viewWidth * 3) - 20, height + RxImageTool.dp2px(10), overNumPaint);

//        canvas.drawText(decimalFormat.format(underWeight), width - paddingLeft + viewWidth - 20, height + RxImageTool.dp2px(10), underNumPaint);
//        canvas.drawText(decimalFormat.format(standardWeight), width - paddingLeft + (viewWidth * 2) - 20, height + RxImageTool.dp2px(10), standardNumPaint);
//        canvas.drawText(decimalFormat.format(overrWeight), width - paddingLeft + (viewWidth * 3) - 20, height + RxImageTool.dp2px(10), overNumPaint);


        RectF rectF = new RectF(width - paddingLeft, RxImageTool.dp2px(20), paddingLeft + viewWidth, RxImageTool.dp2px(26));
        canvas.drawRoundRect(rectF, 10, 10, firstPaint);
        canvas.drawRect(width - paddingLeft + viewWidth - 5, height + RxImageTool.dp2px(20), paddingLeft + (viewWidth * 2), height + RxImageTool.dp2px(26), secondPaint);
        RectF foreRect = new RectF(width - paddingLeft + (viewWidth * 3), height + RxImageTool.dp2px(20), paddingLeft + (viewWidth * 4), height + RxImageTool.dp2px(26));
        canvas.drawRoundRect(foreRect, 10, 10, forePaint);
        canvas.drawRect(width - paddingLeft + (viewWidth * 2), height + RxImageTool.dp2px(20), paddingLeft + (viewWidth * 3) + 5, height + RxImageTool.dp2px(26), threePaint);


        canvas.drawText("偏轻", width - paddingLeft, height + RxImageTool.dp2px(45), underPaint);
        canvas.drawText("标准", width - paddingLeft + viewWidth - 5, height + RxImageTool.dp2px(45), standardPaint);
        canvas.drawText("偏重", width - paddingLeft + (viewWidth * 2), height + RxImageTool.dp2px(45), weightingPaint);
        canvas.drawText("超重", width - paddingLeft + (viewWidth * 3), height + RxImageTool.dp2px(45), overPaint);
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
                RectF circleF = new RectF(paddingLeft + RxImageTool.dp2px(1), height + RxImageTool.dp2px(16), paddingLeft + RxImageTool.dp2px(15), height + RxImageTool.dp2px(30));
                canvas.drawOval(circleF, circleWhitPaint);
                canvas.drawOval(circleF, circlePaint);
            } else if (currentWeight > maxValue) {
                RectF circleF = new RectF(paddingLeft + (viewWidth * 4) - RxImageTool.dp2px(14), height + RxImageTool.dp2px(16), paddingLeft + (viewWidth * 4), height + RxImageTool.dp2px(30));
                canvas.drawOval(circleF, circleWhitPaint);
                canvas.drawOval(circleF, circlePaint);
            } else {
                float v = (currentWeight - minValue) / (maxValue - minValue);
                float currentProgress = (paddingLeft + (viewWidth * 4)) * v;
                RectF circleF = new RectF(currentProgress - RxImageTool.dp2px(7), height + RxImageTool.dp2px(16), currentProgress + RxImageTool.dp2px(7), height + RxImageTool.dp2px(30));
                canvas.drawOval(circleF, circleWhitPaint);
                canvas.drawOval(circleF, circlePaint);
            }

        } else {
            circlePaint.setColor(getResources().getColor(R.color.healthy_first_weight));
            RectF circleF = new RectF(paddingLeft + RxImageTool.dp2px(1), height + RxImageTool.dp2px(16), paddingLeft + RxImageTool.dp2px(15), height + RxImageTool.dp2px(30));
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
