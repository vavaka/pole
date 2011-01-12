package com.vavaka.pole.shapes;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * User: vavaka
 * Date: 12/22/10 4:27 PM
 */
public interface Shape {
    PointF getCenter();
    void setCenter(PointF center);
    double getAngle();
    void setAngle(double angle);
    Path getPath();
    boolean contains(float x, float y);
}
