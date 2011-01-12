package com.vavaka.pole;

import android.graphics.*;
import android.graphics.drawable.Drawable;

/**
 * User: vavaka
 * Date: 12/22/10 6:21 PM
 */
public class Board {
    private static final int ROWS_COUNT = 4;

    private int width;
    private PointF topLeft;
    private Paint borderPaint;
    private Paint backgroundPaint;
    private Paint gridPaint;

    public Board(PointF topLeft, int width) {
        this.topLeft = topLeft;
        this.width = width;

        borderPaint = new Paint();
        borderPaint.setColor(Color.BLACK);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);

        gridPaint = new Paint();
        gridPaint.setColor(Color.GRAY);
    }

    private void drawGrid(Canvas canvas) {
        Path path = new Path();

        int rowWidth = width / ROWS_COUNT;
        for (int row = 1; row < ROWS_COUNT; row++) {
            canvas.drawLine(topLeft.x + rowWidth * row, topLeft.y, topLeft.x + rowWidth * row, topLeft.y + width, gridPaint);
            canvas.drawLine(topLeft.x, topLeft.y + rowWidth * row, topLeft.x + width, topLeft.y + rowWidth * row, gridPaint);
        }
    }

    private void drawBorder(Canvas canvas) {
        canvas.drawLine(topLeft.x, topLeft.y, topLeft.x + width, topLeft.y, borderPaint);
        canvas.drawLine(topLeft.x + width, topLeft.y, topLeft.x + width, topLeft.y + width, borderPaint);
        canvas.drawLine(topLeft.x + width, topLeft.y + width, topLeft.x, topLeft.y + width, borderPaint);
        canvas.drawLine(topLeft.x, topLeft.y + width, topLeft.x, topLeft.y, borderPaint);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawRect(topLeft.x, topLeft.y, topLeft.x + width, topLeft.y + width, backgroundPaint);
    }

    public void draw(Canvas canvas) {
        drawBackground(canvas);
        drawGrid(canvas);
        drawBorder(canvas);
    }
}
