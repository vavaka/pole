package com.vavaka.pole;

import android.graphics.PointF;

/**
 * User: vavaka
 * Date: 12/22/10 4:33 PM
 */
public class Utils {
    public static double getAngelBetweenPoints(PointF center, PointF p1) {
        double radius = Math.sqrt(
                Math.abs(p1.x - center.x) * Math.abs(p1.x - center.x)
                        +
                        Math.abs(p1.y - center.y) * Math.abs(p1.y - center.y)
        );
        PointF p0 = new PointF(center.x, center.y - (float) radius);
        return (2 * Math.atan2(p1.y - p0.y, p1.x - p0.x)) * 180 / Math.PI;
    }

    public static PointF rotate(PointF center, PointF p, double angle){
        double dx = p.x - center.x;
        double dy = p.y - center.y;
        double radians = (angle + 180) * Math.PI / 180;
        double x = center.x - dx*Math.cos(radians) + dy*Math.sin(radians);
        double y = center.y - dx*Math.sin(radians) - dy*Math.cos(radians);
/*

        double x = p.x*Math.cos(angle) - p.y*Math.sin(angle);
        double y = p.y*Math.cos(angle) + p.x*Math.sin(angle);
*/

        return new PointF((float) x, (float) y);
    }
}
