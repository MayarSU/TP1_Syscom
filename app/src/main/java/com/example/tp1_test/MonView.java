package com.example.tp1_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MonView extends View {

    // Coordonnées pour détecter le mouvement
    float x1, x2, y1, y2;

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

        // Démarrage du timer
        timerHandler.postDelayed(updateTimerThread, 10);

        // Listener pour détecter les mouvements tactiles
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

                        // Mouvement principalement horizontal
                        if (Math.abs(dx) > Math.abs(dy)) {

                            if (dx > 0) {
                                direction = "right";
                            } else {
                                direction = "left";
                            }

                        } else {

                            // Mouvement principalement vertical
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
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();

        // Fond noir
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);

        canvas.drawRect(
                0,
                0,
                getWidth(),
                getHeight(),
                p
        );

        // Texte vert
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

        // Charger l'image
        Bitmap b = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.images
        );

        // Afficher l'image
        canvas.drawBitmap(
                b,
                200,
                200,
                p
        );
    }
}