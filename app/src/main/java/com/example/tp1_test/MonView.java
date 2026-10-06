package com.example.tp1_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MonView extends View {

    Handler timerHandler = new Handler();

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timerHandler.postDelayed(this, 100);

            // Redemande l'affichage de la View
            invalidate();
        }
    };

    public MonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        timerHandler.postDelayed(updateTimerThread, 10);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();

        // Fond noir
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);

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
        canvas.drawBitmap(b, 200, 200, p);
    }
}