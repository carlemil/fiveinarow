package se.kjellstrand.fiveinarow.game_page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
    private float tileSize = -1f;

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

        if (board != null) {

            float tileWidth = canvas.getWidth() / board.length;
            float tileHight = canvas.getHeight() / board[0].length;
            float minTileSize = Math.min(tileHight, tileWidth);
            if (minTileSize != tileSize) {
                tileSize = minTileSize;
                playerXmarkBitmap = Bitmap.createScaledBitmap(playerXmarkBitmap, (int) tileSize, (int) tileSize, true);
                playerOmarkBitmap = Bitmap.createScaledBitmap(playerOmarkBitmap, (int) tileSize, (int) tileSize, true);
            }
            Paint paint = new Paint();

            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (board[x][y] != 0) {
                        Bitmap bitmap;
                        bitmap = board[x][y] % 2 == 1 ? playerOmarkBitmap : playerXmarkBitmap;
                        canvas.drawBitmap(bitmap, x * tileSize, y * tileSize, paint);
                    }
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

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
                    if (tileSize != -1) {
                        Move move = new Move();
                        move.x = (int) (event.getX() / tileSize);
                        move.y = (int) (event.getY() / tileSize);
                        humanPlayer.makeMove(move);
                    }
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
