package com.marceme.identityapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class CircularView extends View {


    private static final float DASH_INTERVAL = 2;
    private static final int MARGIN_OFFSET = 40;

    private int radius;
    private Paint squarePaint;
    private int width;
    private int height;

    Paint circlePaint;
    private Bitmap b;

    public CircularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircularView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            b.eraseColor(Color.TRANSPARENT);
            c.drawRect(0, 0, w, h, squarePaint);
            c.drawCircle(width / 2, height / 2, radius, circlePaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        radius = Math.min(width, height) / 2 - MARGIN_OFFSET;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(b, 0, 0, null);
    }

    private void init() {

        circlePaint = new Paint();
        circlePaint.setColor(Color.TRANSPARENT);
        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        circlePaint.setAntiAlias(true);

        squarePaint = new Paint();
        squarePaint.setColor(Color.WHITE);
        squarePaint.setAlpha(220);

    }


}
