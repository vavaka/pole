package com.vavaka.pole.shapes;

import android.graphics.Path;
import android.graphics.PointF;
import com.vavaka.pole.Utils;

/**
 * User: vavaka
 * Date: 12/22/10 4:27 PM
 */
public class RectShape implements Shape {
    private PointF center;
    private int width;
    private int height;
    private double angle;

    public RectShape(int width, int height) {
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
        RectShapePoints shapePoints = rotatePoints(center, angle, calculatePoints(center, width, height));

        Path path = new Path();
        path.moveTo(shapePoints.a.x, shapePoints.a.y);
        path.lineTo(shapePoints.b.x, shapePoints.b.y);
        path.lineTo(shapePoints.c.x, shapePoints.c.y);
        path.lineTo(shapePoints.d.x, shapePoints.d.y);
        path.lineTo(shapePoints.a.x, shapePoints.a.y);

        return path;
    }

    public boolean contains(float x, float y) {
        PointF zeroAnglePoint = Utils.rotate(center, new PointF(x, y), (360 - angle));
        return (zeroAnglePoint.x >= center.x && zeroAnglePoint.x <= center.x + width) && (zeroAnglePoint.y >= center.y - height && zeroAnglePoint.y <= center.y);
    }

    static class RectShapePoints{
        PointF a;
        PointF b;
        PointF c;
        PointF d;
    }

    public static RectShapePoints calculatePoints(PointF center, float width, float height) {
        RectShapePoints shapePoints = new RectShapePoints();
        shapePoints.a = new PointF(center.x, center.y);
        shapePoints.b = new PointF(center.x, center.y - height);
        shapePoints.c = new PointF(center.x + width, center.y - height);
        shapePoints.d = new PointF(center.x + width, center.y);

        return shapePoints;
    }

    public static RectShapePoints rotatePoints(PointF center, double angle, RectShapePoints shapePoints) {
        RectShapePoints rotatedShapePoints = new RectShapePoints();
        rotatedShapePoints.a = Utils.rotate(center, shapePoints.a, angle);
        rotatedShapePoints.b = Utils.rotate(center, shapePoints.b, angle);
        rotatedShapePoints.c = Utils.rotate(center, shapePoints.c, angle);
        rotatedShapePoints.d = Utils.rotate(center, shapePoints.d, angle);

        return rotatedShapePoints;
    }
}
