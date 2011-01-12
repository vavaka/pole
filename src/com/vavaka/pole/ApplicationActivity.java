package com.vavaka.pole;

import android.app.Activity;
import android.os.Bundle;

public class ApplicationActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameCanvas gameCanvas = new GameCanvas(this);
        setContentView(gameCanvas);
    }
}
