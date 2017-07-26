package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice11PieChartView extends View {

    private Paint paint = new Paint();

    private int width, height,
            size, radius,
            startX, startY,
            centreX, centreY,
            startAngle,
            nameMargin;

    private String[] names = {"米汤", "星", "蛋哥", "杰哥", "芳哥", "超子"};
    private int[] progresses = {15, 40, 10, 11, 8, 16};
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

        radius = size / 2;
        centreX = startX + radius;
        centreY = startY + radius;

        nameMargin = radius + 50;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);

        startAngle = 0;
        int progress,
                lineStartX, lineStartY,
                lineEndX, lineEndY,
                nameLength, nameHeight;
        String name;
        double centerAngle;
        int len = names.length;
        for (int i = 0; i < len; i++) {
            if (i == len - 1)
                progress = 360 - startAngle;
            else
                progress = progresses[i] * 360 / 100;

            paint.setColor(colors[i]);
            canvas.drawArc(startX, startY, startX + size, startY + size, startAngle, progress - 2, true, paint);

            centerAngle = progress / 2 + startAngle;

            if (90 > centerAngle && centerAngle > 0) {
                centerAngle = centerAngle * Math.PI / 180;
                lineStartX = (int) (radius * Math.cos(centerAngle));
                lineStartY = (int) (radius * Math.sin(centerAngle));

                if (lineStartX > lineStartY) {
                    lineEndX = radius;
                    lineEndY = (int) (lineEndX * Math.tan(centerAngle));
                } else {
                    lineEndY = radius;
                    lineEndX = (int) (lineEndY / Math.tan(centerAngle));
                }

                lineStartX += centreX;
                lineStartY += centreY;
                lineEndX += centreX;
                lineEndY += centreY;

            } else if (180 > centerAngle && centerAngle >= 90) {
                centerAngle = (centerAngle - 90) * Math.PI / 180;
                lineStartX = (int) (radius * Math.sin(centerAngle));
                lineStartY = (int) (radius * Math.cos(centerAngle));

                if (lineStartX > lineStartY) {
                    lineEndX = radius;
                    lineEndY = (int) (lineEndX / Math.tan(centerAngle));
                } else {
                    lineEndY = radius;
                    lineEndX = (int) (lineEndY * Math.tan(centerAngle));
                }

                lineStartX = centreX - lineStartX;
                lineStartY += centreY;
                lineEndX = centreX - lineEndX;
                lineEndY += centreY;
            } else if (270 > centerAngle && centerAngle >= 180) {
                centerAngle = (centerAngle - 180) * Math.PI / 180;
                lineStartX = (int) (radius * Math.cos(centerAngle));
                lineStartY = (int) (radius * Math.sin(centerAngle));

                if (lineStartX > lineStartY) {
                    lineEndX = radius;
                    lineEndY = (int) (lineEndX * Math.tan(centerAngle));
                } else {
                    lineEndY = radius;
                    lineEndX = (int) (lineEndY / Math.tan(centerAngle));
                }

                lineStartX = centreX - lineStartX;
                lineStartY = centreY - lineStartY;
                lineEndX = centreX - lineEndX;
                lineEndY = centreY - lineEndY;
            } else {
                centerAngle = (centerAngle - 270) * Math.PI / 180;
                lineStartX = (int) (radius * Math.sin(centerAngle));
                lineStartY = (int) (radius * Math.cos(centerAngle));

                if (lineStartX > lineStartY) {
                    lineEndX = radius;
                    lineEndY = (int) (lineEndX / Math.tan(centerAngle));
                } else {
                    lineEndY = radius;
                    lineEndX = (int) (lineEndY * Math.tan(centerAngle));
                }

                lineStartX += centreX;
                lineStartY = centreY - lineStartY;
                lineEndX += centreX;
                lineEndY = centreY - lineEndY;
            }
            paint.setColor(Color.WHITE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);


            centerAngle = progress / 2 + startAngle;
            name = names[i];
            nameLength = (int) paint.measureText(name);
            Rect rect = new Rect();
            paint.getTextBounds(name, 0, name.length(), rect);
            paint.setTextSize(30);
            nameHeight = rect.height() / 2;
            if (centerAngle > 90 && centerAngle < 270) {//左边
                canvas.drawLine(lineEndX, lineEndY, centreX - nameMargin, lineEndY, paint);
                canvas.drawText(name, centreX - nameMargin - nameLength, lineEndY + nameHeight, paint);
            } else {//右边
                canvas.drawLine(lineEndX, lineEndY, centreX + nameMargin, lineEndY, paint);
                canvas.drawText(name, centreX + nameMargin, lineEndY + nameHeight, paint);
            }

            startAngle += progress;
        }

    }
}
