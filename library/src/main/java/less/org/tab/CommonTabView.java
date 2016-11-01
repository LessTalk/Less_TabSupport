
package less.org.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/8/16
 * DESCRIPTION
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public abstract class CommonTabView extends View {

    public CommonTabView(Context context) {
        super(context);
    }

    public CommonTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void init(float radius,int width,int height);

    public abstract void draw(float interpolatedTime);

    public abstract float getRadius();

    public abstract void setCurrentColor(int color);
}
