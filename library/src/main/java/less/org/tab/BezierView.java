
package less.org.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/8/16
 * DESCRIPTION
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class BezierView extends CommonTabView {

    private static final String TAG = "Less" + BezierView.class.getSimpleName();

    private static final float M = 0.551915024494f;

    private Path mPath;
    private Paint mPaint;

    private int centerX, centerY;

    private int multiple = 0;

    private float radius, stretchDistance, cDistance, mInterpolatedTime;

    private float maxLength;

    private VPoint p2, p4;
    private HPoint p1, p3;

    private float c;

    private Context mContext;

    public BezierView(Context context) {
        super(context);
        init(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void init(float radius, int width, int height) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "init-radius:" + radius + "|width:" + width + "|height:" + height);
        }
        this.radius = radius;
        c = radius * M;
        cDistance = (c * 0.45f);
        maxLength = width;
        centerX = width / 2;
        centerY = height / 2;
        stretchDistance = radius;
    }

    private void init(Context context) {
        this.mContext = context;

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);

        mPath = new Path();

        p2 = new VPoint();
        p4 = new VPoint();

        p1 = new HPoint();
        p3 = new HPoint();
    }

    public void draw(float interpolatedTime) {
        multiple = 0;
        while(interpolatedTime > 1){
            interpolatedTime -=1;
            multiple ++;
        }

        mInterpolatedTime = interpolatedTime;
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "draw-interpolatedTime:" + interpolatedTime+"|centerX:"+centerX);
        }
        invalidate();
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public void setCurrentColor(int color) {
        mPaint.setColor(color);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.translate(centerX+ (centerX * multiple * 2), centerY);

        if (mInterpolatedTime >= 0 && mInterpolatedTime <= 0.2) {
            model1(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.2 && mInterpolatedTime <= 0.5) {
            model2(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.5 && mInterpolatedTime <= 0.8) {
            model3(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.8 && mInterpolatedTime <= 0.9) {
            model4(mInterpolatedTime);
        } else if (mInterpolatedTime > 0.9 && mInterpolatedTime <= 1) {
            model5(mInterpolatedTime);
        }

        float offset = maxLength * (mInterpolatedTime - 0.2f);
        offset = offset > 0 ? offset : 0;
        p1.adjustAllX(offset);
        p2.adjustAllX(offset);
        p3.adjustAllX(offset);
        p4.adjustAllX(offset);

        mPath.moveTo(p1.x, p1.y);
        mPath.cubicTo(p1.right.x, p1.right.y, p2.bottom.x, p2.bottom.y, p2.x, p2.y);
        mPath.cubicTo(p2.top.x, p2.top.y, p3.right.x, p3.right.y, p3.x, p3.y);
        mPath.cubicTo(p3.left.x, p3.left.y, p4.top.x, p4.top.y, p4.x, p4.y);
        mPath.cubicTo(p4.bottom.x, p4.bottom.y, p1.left.x, p1.left.y, p1.x, p1.y);

        canvas.drawPath(mPath, mPaint);

    }

    class VPoint {
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void setX(float x) {
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset) {
            top.y -= offset;
            bottom.y += offset;
        }

        public void adjustAllX(float offset) {
            this.x += offset;
            top.x += offset;
            bottom.x += offset;
        }
    }

    class HPoint {
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void setY(float y) {
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustAllX(float offset) {
            this.x += offset;
            left.x += offset;
            right.x += offset;
        }
    }

    private void model0() {
        p1.setY(radius);
        p3.setY(-radius);
        p3.x = p1.x = 0;
        p3.left.x = p1.left.x = -c;
        p3.right.x = p1.right.x = c;

        p2.setX(radius);
        p4.setX(-radius);
        p2.y = p4.y = 0;
        p2.top.y = p4.top.y = -c;
        p2.bottom.y = p4.bottom.y = c;
    }

    private void model1(float time) {//0~0.2
        model0();
        p2.setX(radius + stretchDistance * time * 5);
    }

    private void model2(float time) {//0.2~0.5
        model1(0.2f);
        time = (time - 0.2f) * (10f / 3);
        p1.adjustAllX(stretchDistance / 2 * time);
        p3.adjustAllX(stretchDistance / 2 * time);
        p2.adjustY(cDistance * time);
        p4.adjustY(cDistance * time);
    }

    private void model3(float time) {//0.5~0.8
        model2(0.5f);
        time = (time - 0.5f) * (10f / 3);
        p1.adjustAllX(stretchDistance / 2 * time);
        p3.adjustAllX(stretchDistance / 2 * time);
        p2.adjustY(-cDistance * time);
        p4.adjustY(-cDistance * time);

        p4.adjustAllX(stretchDistance / 2 * time);

    }

    private void model4(float time) {//0.8~0.9
        model3(0.8f);
        time = (time - 0.8f) * 10;
        p4.adjustAllX(stretchDistance / 2 * time);
    }

    private void model5(float time) {
        model4(0.9f);
        time = time - 0.9f;
        p4.adjustAllX((float) (Math.sin(Math.PI * time * 10f) * (2 / 10f * radius)));
    }

    private static int dpToPx(Context context, int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    private class MoveAnimation extends Animation {

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            invalidate();
        }
    }

    public void startAnimation() {
        mPath.reset();
        mInterpolatedTime = 0;
        MoveAnimation move = new MoveAnimation();
        move.setDuration(1000);
        move.setInterpolator(new AccelerateDecelerateInterpolator());
        startAnimation(move);
    }
}
