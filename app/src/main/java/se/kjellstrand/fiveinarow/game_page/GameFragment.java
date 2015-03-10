package se.kjellstrand.fiveinarow.game_page;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.kjellstrand.fiveinarow.R;
import se.kjellstrand.fiveinarow.game_model.FiveInARow;
import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.players.AbstractPlayer;
import se.kjellstrand.fiveinarow.game_model.players.RandomPlayer;
import se.kjellstrand.fiveinarow.game_model.players.RandomSearchPlayer;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link se.kjellstrand.fiveinarow.game_page.GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link se.kjellstrand.fiveinarow.game_page.GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private BoardView boardView;
    private FiveInARow fiveInARowGame;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        return fragment;
    }

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AbstractPlayer p1 = new RandomPlayer(1);
        AbstractPlayer p2 = new RandomSearchPlayer(2);
        FiveInARowBoard fiveInARowGameBoard = new FiveInARowBoard(19, 19, p1, p2);
        fiveInARowGame = new FiveInARow(fiveInARowGameBoard);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        boardView = (BoardView) view.findViewById(R.id.game_board);

        view.findViewById(R.id.start_game_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        return view;
    }

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

    private void play() {
        new AsyncTask<Integer, Integer, Integer>(){
            @Override
            protected Integer doInBackground(Integer... params) {
                fiveInARowGame.playTheGame(boardView);
                return null;
            }
        }.execute(new Integer(1));
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
