package com.vavaka.pole;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import com.vavaka.pole.shapes.CornerShape;
import com.vavaka.pole.shapes.RectShape;

import java.util.HashMap;
import java.util.Map;

/**
 * User: vavaka
 * Date: 12/22/10 4:39 PM
 */
public class GameCanvas extends View {
    private Map<String, ShapeContext> allShapeContexts = new HashMap<String, ShapeContext>();
    private Map<String, ShapeContext> mapShapeContexts = new HashMap<String, ShapeContext>();
    private Map<String, ShapeContext> toolbarShapeContexts = new HashMap<String, ShapeContext>();
    private String dragShapeId = null;
    private PointF dragOffset = null;
    private Board board = new Board(new PointF(0, 30), 320);

    public GameCanvas(Context context) {
        super(context);

        initializeShapeContexts();
    }

    private void initializeShapeContexts() {
        ShapeContext greenShapeContext = new ShapeContext();
        greenShapeContext.bigShape = new CornerShape(80, 160);
        greenShapeContext.bigShape.setCenter(new PointF(100, 200));
        greenShapeContext.bigShape.setAngle(0);
        greenShapeContext.smallShape = new CornerShape(20, 40);
        greenShapeContext.smallShape.setCenter(new PointF(100, 100));
        greenShapeContext.smallShape.setAngle(0);
        greenShapeContext.paint = new Paint();
        greenShapeContext.paint.setColor(Color.GREEN);
        greenShapeContext.paint.setAntiAlias(true);
        greenShapeContext.paint.setAlpha(150);
        allShapeContexts.put("green", greenShapeContext);
        mapShapeContexts.put("green", greenShapeContext);

        ShapeContext blueShapeContext = new ShapeContext();
        blueShapeContext.bigShape = new RectShape(80, 160);
        blueShapeContext.bigShape.setCenter(new PointF(50, 200));
        blueShapeContext.bigShape.setAngle(0);
        blueShapeContext.smallShape = new CornerShape(20, 40);
        blueShapeContext.smallShape.setCenter(new PointF(50, 100));
        blueShapeContext.smallShape.setAngle(0);
        blueShapeContext.paint = new Paint();
        blueShapeContext.paint.setColor(Color.BLUE);
        blueShapeContext.paint.setAntiAlias(true);
        blueShapeContext.paint.setAlpha(150);
        allShapeContexts.put("blue", blueShapeContext);
        mapShapeContexts.put("blue", blueShapeContext);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        board.draw(canvas);

        for (ShapeContext shapeContext : allShapeContexts.values()) {
            canvas.drawPath(shapeContext.bigShape.getPath(), shapeContext.paint);
        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (Map.Entry<String, ShapeContext> entry : mapShapeContexts.entrySet()) {
                if (entry.getValue().bigShape.contains(event.getX(), event.getY())) {
                    dragShapeId = entry.getKey();

                    float offsetX = entry.getValue().bigShape.getCenter().x - event.getX();
                    float offsetY = entry.getValue().bigShape.getCenter().y - event.getY();
                    dragOffset = new PointF(offsetX, offsetY);

                    break;
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (dragShapeId != null) {
                ShapeContext shapeContext = mapShapeContexts.get(dragShapeId);
                shapeContext.bigShape.setCenter(new PointF(event.getX() + dragOffset.x, event.getY() + dragOffset.y));
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            dragShapeId = null;
        }

        invalidate();

        return true;
    }
}
