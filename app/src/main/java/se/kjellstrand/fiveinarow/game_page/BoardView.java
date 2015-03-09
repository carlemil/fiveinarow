package se.kjellstrand.fiveinarow.game_page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import se.kjellstrand.fiveinarow.R;

/**
 * Created by carlemil on 3/9/15.
 */
public class BoardView extends View {

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

        // Draw the shadow
        Paint paint = new Paint();
        paint.setColor(0xff00ff00);
//        RectF a = new RectF(0, 0, 200, 200);
//        canvas.drawOval(a, paint);
        canvas.drawBitmap(backgroundBitmap, getMatrix(), paint);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //getMatrix().preScale()
    }

    public void setBoard(int[][] _board) {
        this.board = _board;
    }
}
