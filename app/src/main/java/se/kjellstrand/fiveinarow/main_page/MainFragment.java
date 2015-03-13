package se.kjellstrand.fiveinarow.main_page;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import se.kjellstrand.fiveinarow.R;
import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;
import se.kjellstrand.fiveinarow.game_model.players.AbstractPlayer;
import se.kjellstrand.fiveinarow.game_model.players.RandomPlayer;
import se.kjellstrand.fiveinarow.game_page.GameActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ((Button) view.findViewById(R.id.start_game_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.newInstance(getActivity());
            }
        });

//        ((Button) view.findViewById(R.id.test_check_speed_button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                testSpeedOfGetState();
//            }
//
//        });

        return view;
    }

//    private void testSpeedOfGetState() {
//
//        AbstractPlayer p1 = new RandomPlayer(1);
//        AbstractPlayer p2 = new RandomPlayer(2);
//        FiveInARowBoard fiveInARowGameBoard = new FiveInARowBoard(15, 15, p1, p2);
//        for (int i = 0; i < 15 * 2 ; i++) {
//            Move m = p1.getNextMove(fiveInARowGameBoard);
//            fiveInARowGameBoard.makeMove(m, p1);
//            m = p2.getNextMove(fiveInARowGameBoard);
//            fiveInARowGameBoard.makeMove(m, p2);
//        }
//        fiveInARowGameBoard.print();
//
//        long t1 = SystemClock.uptimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            for (int y = 0; y < 15; y++) {
//                for (int x = 0; x < 15; x++) {
//                    fiveInARowGameBoard.getState(new Move(x, y));
//                }
//            }
//        }
//
//        long t2 = SystemClock.uptimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            for (int y = 0; y < 15; y++) {
//                for (int x = 0; x < 15; x++) {
//                    fiveInARowGameBoard.getState2(new Move(x, y));
//                }
//            }
//        }
//
//        long t3 = SystemClock.uptimeMillis();
//
//        Log.d(TAG, "s1: "+(t2-t1)+" s2: "+(t3-t2));
//
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    protected interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
