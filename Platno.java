package com.example.zapoctak;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Platno extends View {

    Bitmap mBitmapa = null;
    Canvas mCanvas = null;
    Path cesta;
    Paint mPaint;
    Paint mGuma;


    boolean gumaAktiv = false;



    public Platno(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("medard", "konsturktor Platno byl zavolan");
        init();
    }




    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(12);
        mPaint.setStyle(Paint.Style.STROKE);

        mGuma = new Paint(mPaint);
        mGuma.setStrokeWidth(80);
        mGuma.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        cesta = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mBitmapa == null) {
            mBitmapa = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }
        mCanvas = new Canvas(mBitmapa);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("platno", "dotknuto " + event.getAction());
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cesta.moveTo(event.getX(), event.getY());
                kreslit();
                Log.d("platno", "action down");
                break;
            case MotionEvent.ACTION_MOVE:
                cesta.lineTo(event.getX(), event.getY());
                kreslit();
                Log.d("platno", "action move");
                break;
            case MotionEvent.ACTION_UP:
                cesta.lineTo(event.getX(), event.getY());
                kreslit();
                Log.d("platno", "action up");
                cesta.reset();
                break;

        }
        return true;
    }

    public void zmenVelikost(float a) {
        if (a < 12) {
            mPaint.setStrokeWidth(12);
            mGuma.setStrokeWidth(12);
        } else {
            mPaint.setStrokeWidth(a);
            mGuma.setStrokeWidth(a);
        }

    }


    private void kreslit() {
        //Path cesta = new Path();
        //Paint mPaint = new Paint(); přesunuty výš aby byly dostupne jinym metodam
        if(gumaAktiv == true) {
            mCanvas.drawPath(cesta, mGuma);
        } else {
            mCanvas.drawPath(cesta, mPaint);
        }
        invalidate(); //pro prekresleni ty mapy po zaznamenani kde ma byt neco zakresleno

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {


        super.onDraw(canvas);
        canvas.drawBitmap(mBitmapa, 0, 0,  mPaint);

    }

    public void zmenStetec(int cislo) {
        mPaint.setColor(cislo);

    }

    public void nastavGumu (boolean stav) {
        gumaAktiv = stav;
    }


}
