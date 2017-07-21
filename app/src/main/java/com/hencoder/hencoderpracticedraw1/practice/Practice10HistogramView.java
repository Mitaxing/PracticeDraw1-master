package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice10HistogramView extends View {

    private int width, height,
            startX, startY,
            textHeight = 30,
            contentHeight,
            rectWidth = 80, rectMargin, rectNumber = 7;

    private String[] names = {"米汤", "星", "蛋哥", "杰哥", "芳哥", "陆超月", "刚哥"};
    private int[] progress = {80, 100, 50, 5, 35, 72, 33};

    private Paint paint = new Paint();

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        startX = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.1);
        startY = (int) (MeasureSpec.getSize(heightMeasureSpec) * 0.1);

        width = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.8);
        height = (int) (MeasureSpec.getSize(heightMeasureSpec) * 0.8);

        rectMargin = (width - rectNumber * rectWidth) / (rectNumber + 1);
        contentHeight = height - textHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.WHITE);
        canvas.drawLine(startX, startY, startX, startY + height - textHeight, paint);
        canvas.drawLine(startX, startY + height - textHeight, startX + width, startY + height - textHeight, paint);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        String name;
        int len = names.length,
                nameLen;
        for (int i = 0; i < len; i++) {

            paint.setColor(Color.GREEN);
            canvas.drawRect(startX + (i + 1) * rectMargin + i * rectWidth,
                    startY + (100 - progress[i]) * contentHeight / 100,
                    startX + (i + 1) * rectMargin + (i + 1) * rectWidth,
                    startY + contentHeight,
                    paint);

            name = names[i];
            nameLen = (int) paint.measureText(name) / 2;

            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText(name,
                    startX + (i + 1) * rectMargin + (i * rectWidth) + rectWidth / 2 - nameLen,
                    startY + height,
                    paint);
        }

    }
}
