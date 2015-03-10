package se.kjellstrand.fiveinarow.game_page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import se.kjellstrand.fiveinarow.R;

/**
 * Created by carlemil on 3/9/15.
 */
public class BoardView extends View {

    private static final String TAG = BoardView.class.getSimpleName();
    private int[][] board;

    Bitmap playerXmarkBitmap;
    Bitmap playerOmarkBitmap;
    Bitmap backgroundBitmap;

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        playerXmarkBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player_x_mark);
        playerOmarkBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.player_o_mark);
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.board_background);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        // Draw the shadow

        //canvas.drawBitmap(backgroundBitmap, getMatrix(), paint);

        paint.setColor(0xff00ff00);
        RectF a = new RectF(0, 0, (int)(Math.random()*500f), (int)(Math.random()*500f));
        canvas.drawOval(a, paint);
        Log.d(TAG, "draw");

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //getMatrix().preScale()
    }

    public void redraw(int[][] _board) {
        this.board = _board;
        postInvalidate();
        Log.d(TAG, "redraw");
    }
}
