package vlu.android.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasketballFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketballFragment extends Fragment {
    View view;
    ListView listView;
    ArrayList<Match> matchList;
    CustomAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasketballFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketballFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasketballFragment newInstance(String param1, String param2) {
        BasketballFragment fragment = new BasketballFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_soccer, container, false);
        listView = view.findViewById(R.id.listView);

        // Sample data
        matchList = new ArrayList<>();
        matchList.add(new Match(R.drawable.logo_baseket_1, R.drawable.logo_baseket_2, "Golden State Warriors", "Charlotte Hornets", "98", "75", "4th"));
        matchList.add(new Match(R.drawable.logo_baseket_3, R.drawable.logo_baseket_4, "Atlanta Hawks", "Chicago Bulls", "77", "82", "4th"));

        adapter = new CustomAdapter(getActivity(), matchList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Match selectedMatch = matchList.get(position);
                MatchDetailFragment matchDetailFragment = MatchDetailFragment.newInstance(
                        selectedMatch.getTeamLogo1(),
                        selectedMatch.getTeamLogo2(),
                        selectedMatch.getTeamName1(),
                        selectedMatch.getTeamName2(),
                        selectedMatch.getTeamScore1(),
                        selectedMatch.getTeamScore2(),
                        selectedMatch.getMatchStatus()
                );

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frmLayout, matchDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}