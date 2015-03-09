package se.kjellstrand.fiveinarow.game_page;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by carlemil on 3/9/15.
 */
public class BoardView extends View {

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the shadow
        Paint paint = new Paint();
        paint.setColor(0xff00ff00);
        RectF a = new RectF(0, 0, 200, 200);
        canvas.drawOval(
                a,
                paint
        );


    }
}
