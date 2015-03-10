package se.kjellstrand.fiveinarow.game_page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import se.kjellstrand.fiveinarow.R;
import se.kjellstrand.fiveinarow.game_model.Move;
import se.kjellstrand.fiveinarow.game_model.players.HumanPlayer;

/**
 * Created by carlemil on 3/9/15.
 */
public class BoardView extends View {

    private static final String TAG = BoardView.class.getSimpleName();
    private int[][] board;

    private Bitmap playerXmarkBitmap;
    private Bitmap playerOmarkBitmap;
    private Bitmap backgroundBitmap;

    private HumanPlayer humanPlayer;

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
        RectF a = new RectF(0, 0, (int) (Math.random() * 500f), (int) (Math.random() * 500f));
        canvas.drawOval(a, paint);
        Log.d(TAG, "draw");

        if (board != null) {
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (board[x][y] != 0) {
                        canvas.drawBitmap(playerXmarkBitmap, x * 100, y * 100, paint);
                    }
                }
            }
        }

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

    public void setHumanPlayer(final HumanPlayer humanPlayer) {
        Log.d(TAG, "setHumanPlayer");
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "click " + event.toString());
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Move move = new Move();
                    move.x = (int) (event.getRawX() / 100);
                    move.y = (int) (event.getRawY() / 100);
                    humanPlayer.makeMove(move);
                    // Do what you want
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    return true;
                }
                return false;
            }
        });
    }
}
