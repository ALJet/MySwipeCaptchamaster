package indi.aljet.myswipecaptcha_master;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CtView extends View {
    private static final String TAG = "aljet";

    private Bitmap leftBitmap;

    public CtView(Context context) {
        super(context);
    }

    public CtView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CtView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
                (R.drawable.pic11)),0,0,
        new Paint());
        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(200,100);
        path.lineTo(200,200);
        path.lineTo(200,300);
        path.close();
        Bitmap rightBitmap = getMaskBitmap(BitmapFactory
        .decodeResource(getResources(),(R.drawable.pic11)),
                path);
        canvas.drawBitmap(rightBitmap,-100,-100,
        new Paint());
    }


    private Bitmap getMaskBitmap(Bitmap mBitmap,Path mask){
        Bitmap bgBitmap = Bitmap.createBitmap(mBitmap
        .getWidth(),mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Log.e(TAG, " getRightBitmap: " + bgBitmap.getWidth());
        //把创建的位图作为画板
        Canvas mCanvas = new Canvas(bgBitmap);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //mCanvas.save();
        //先将canvas保存
        //把canvas修剪成指定的路径区域
        //mCanvas.translate(100, 100);

        mCanvas.clipPath(mask);
        mCanvas.drawBitmap(mBitmap,0,0,mPaint);
        Log.e(TAG, "getRightBitmap: " + bgBitmap.getWidth());
        return bgBitmap;
    }



}
