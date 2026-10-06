package com.example.tp1_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MonView extends View {

    // Coordonnées pour les mouvements tactiles
    float x1, x2, y1, y2;

    // Valeurs de l'accéléromètre
    private int pencheH, pencheV, penche;

    // Timer
    Handler timerHandler = new Handler();

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timerHandler.postDelayed(this, 100);

            // Redessine la View
            invalidate();
        }
    };

    public MonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // -------------------------
        // TIMER
        // -------------------------
        timerHandler.postDelayed(updateTimerThread, 10);

        // -------------------------
        // TOUCH / SWIPE
        // -------------------------
        OnTouchListener onTouchListener = new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float dx, dy;
                String direction;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        x1 = event.getX();
                        y1 = event.getY();

                        Log.i("pacman", "appuyé");

                        break;

                    case MotionEvent.ACTION_UP:

                        x2 = event.getX();
                        y2 = event.getY();

                        dx = x2 - x1;
                        dy = y2 - y1;

                        if (Math.abs(dx) > Math.abs(dy)) {

                            if (dx > 0) {
                                direction = "right";
                            } else {
                                direction = "left";
                            }

                        } else {

                            if (dy > 0) {
                                direction = "down";
                            } else {
                                direction = "up";
                            }
                        }

                        Log.i("pacman", "laché " + direction);
                        Log.i("pacman", "dx = " + dx + "; dy = " + dy);

                        break;
                }

                invalidate();
                return true;
            }
        };

        setOnTouchListener(onTouchListener);

        // -------------------------
        // ACCELEROMETRE
        // -------------------------
        Sensor accelerometre;

        SensorManager m =
                (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        accelerometre =
                m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final SensorEventListener mSensorEventListener =
                new SensorEventListener() {

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    }

                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {

                        pencheH = -(int) (sensorEvent.values[0]);
                        pencheV = (int) (sensorEvent.values[1]);

                        penche =
                                pencheH * pencheH
                                        + pencheV * pencheV;

                        Log.i(
                                "accelerometre",
                                "H=" + pencheH
                                        + " V=" + pencheV
                                        + " penche=" + penche
                        );
                    }
                };

        m.registerListener(
                mSensorEventListener,
                accelerometre,
                SensorManager.SENSOR_DELAY_UI
        );
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();

        // -------------------------
        // FOND NOIR
        // -------------------------
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);

        canvas.drawRect(
                0,
                0,
                getWidth(),
                getHeight(),
                p
        );

        // -------------------------
        // TEXTE VERT
        // -------------------------
        p.setColor(Color.GREEN);
        p.setTextSize(100);
        p.setTextAlign(Paint.Align.CENTER);

        String texte = "Bonjour MONDE";

        canvas.drawText(
                texte,
                getWidth() / 2f,
                getHeight() / 2f,
                p
        );

        // -------------------------
        // IMAGE
        // -------------------------
        Bitmap b = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.images
        );

        canvas.drawBitmap(
                b,
                200,
                200,
                p
        );
    }
}