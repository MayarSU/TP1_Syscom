package com.example.tp1_test;

import android.content.Context;
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
        Paint p = new Paint();
        /* couleur de l'objet de dessin */
        p.setColor(Color.BLACK);
        /* style : remplissage */
        p.setStyle(Paint.Style.FILL);
        /* rectangle qui occupe toute la View */
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        /* autre couleur pour le texte */
        p.setColor(Color.GREEN);
        /* taille du texte */
        p.setTextSize(100);
        /* le centre du texte est son origine */
        p.setTextAlign(Paint.Align.CENTER);
        /* texte au centre de la View */
        String texte = "Bonjour MONDE";
        canvas.drawText(texte, getWidth() / 2, getHeight() / 2, p);
    }
}