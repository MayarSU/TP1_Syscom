package com.example.tp1_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MonView extends View {

    public MonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();

        // Fond noir
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);

        // Texte vert
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        p.setTextAlign(Paint.Align.CENTER);

        String texte = "Bonjour MONDE";
        canvas.drawText(
                texte,
                getWidth() / 2f,
                getHeight() / 2f,
                p
        );

        // Charger l'image depuis res/drawable/images.png
        Bitmap b = BitmapFactory.decodeResource(
                getResources(),
                R.drawable.images
        );

        // Afficher l'image
        canvas.drawBitmap(b, 200, 200, p);
    }
}