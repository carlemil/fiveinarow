package se.kjellstrand.fiveinarow.game_page;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import se.kjellstrand.fiveinarow.R;

public class GameActivity extends ActionBarActivity implements GameFragment.OnFragmentInteractionListener {

    private static final String TAG = GameActivity.class.getSimpleName();

    /**
     * Use this factory method to create a new instance of
     * this activity using the provided parameters.
     *
     * @param activity A activity to use for context.
     */
    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity, GameActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (savedInstanceState == null) {
            GameFragment gameFragment = GameFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, gameFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "uri: " + uri.toString());
    }
}
