package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice11PieChartView extends View {

    private Paint paint = new Paint();

    private int width, height,
            size,
            startX, startY,
            startAngle;

    private String[] names = {"米汤", "星", "蛋哥", "杰哥", "芳哥", "陆超月", "刚哥"};
    private int[] progresses = {20, 40, 10, 4, 8, 16, 2};
    private int[] colors = {Color.BLUE, Color.RED, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN, Color.YELLOW};

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        size = width < height ? width : height;
        size = (int) (0.8 * size);

        startX = (width - size) / 2;
        startY = (height - size) / 2;

        startAngle = 180;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        paint.setAntiAlias(true);
        startAngle = 0;
        int progress;
        int len = names.length;
        for (int i = 0; i < len; i++) {
            if (i == len - 1)
                progress = 360 - startAngle;
            else
                progress = progresses[i] * 360 / 100;

            paint.setColor(colors[i]);
            canvas.drawArc(startX, startY, startX + size, startY + size, startAngle, progress - 2, true, paint);
            startAngle += progress;
        }

    }
}
