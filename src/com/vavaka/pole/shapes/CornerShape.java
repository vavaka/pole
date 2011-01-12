package com.vavaka.pole.shapes;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import com.vavaka.pole.Utils;

/**
 * User: vavaka
 * Date: 12/22/10 4:26 PM
 */
public class CornerShape implements Shape {
    private PointF center;
    private int width;
    private int height;
    private double angle;

    public CornerShape(int width, int height) {
        this.center = new PointF();
        this.width = width;
        this.height = height;
    }

    public PointF getCenter() {
        return center;
    }

    public void setCenter(PointF center) {
        this.center.set(center.x, center.y);
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Path getPath() {
        CornerShapePoints shapePoints = rotatePoints(center, angle, calculatePoints(center, width, height));

        Path path = new Path();
        path.moveTo(shapePoints.a.x, shapePoints.a.y);
        path.lineTo(shapePoints.b.x, shapePoints.b.y);
        path.lineTo(shapePoints.c.x, shapePoints.c.y);
        path.lineTo(shapePoints.d.x, shapePoints.d.y);
        path.lineTo(shapePoints.e.x, shapePoints.e.y);
        path.lineTo(shapePoints.f.x, shapePoints.f.y);
        path.lineTo(shapePoints.a.x, shapePoints.a.y);
        return path;
    }

    public boolean contains(float x, float y) {
        PointF zeroAnglePoint = Utils.rotate(center, new PointF(x, y), (360 - angle));

        boolean c1 = (zeroAnglePoint.x >= center.x && zeroAnglePoint.x <= center.x + width) && (zeroAnglePoint.y <= center.y && zeroAnglePoint.y >= center.y - height);
        boolean c2 = (zeroAnglePoint.x >= center.x && zeroAnglePoint.x <= center.x + height) && (zeroAnglePoint.y >= center.y - width && zeroAnglePoint.y <= center.y);
        return c1 || c2;
    }

    static class CornerShapePoints{
        PointF a;
        PointF b;
        PointF c;
        PointF d;
        PointF e;
        PointF f;
    }

    public static CornerShapePoints calculatePoints(PointF center, float width, float height) {
        CornerShapePoints shapePoints = new CornerShapePoints();
        shapePoints.a = new PointF(center.x, center.y);
        shapePoints.b = new PointF(center.x, center.y - height);
        shapePoints.c = new PointF(center.x + width, center.y - height);
        shapePoints.d = new PointF(center.x + width, center.y - width);
        shapePoints.e = new PointF(center.x + height, center.y - width);
        shapePoints.f = new PointF(center.x + height, center.y);

        return shapePoints;
    }

    public static CornerShapePoints rotatePoints(PointF center, double angle, CornerShapePoints shapePoints) {
        CornerShapePoints rotatedShapePoints = new CornerShapePoints();
        rotatedShapePoints.a = Utils.rotate(center, shapePoints.a, angle);
        rotatedShapePoints.b = Utils.rotate(center, shapePoints.b, angle);
        rotatedShapePoints.c = Utils.rotate(center, shapePoints.c, angle);
        rotatedShapePoints.d = Utils.rotate(center, shapePoints.d, angle);
        rotatedShapePoints.e = Utils.rotate(center, shapePoints.e, angle);
        rotatedShapePoints.f = Utils.rotate(center, shapePoints.f, angle);

        return rotatedShapePoints;
    }
}
