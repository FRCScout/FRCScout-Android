package com.alphadevelopmentsolutions.frcscout.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alphadevelopmentsolutions.frcscout.Activities.MainActivity;
import com.alphadevelopmentsolutions.frcscout.Adapters.ScoutCardsRecyclerViewAdapter;
import com.alphadevelopmentsolutions.frcscout.Classes.FontAwesomeIcon;
import com.alphadevelopmentsolutions.frcscout.Classes.Match;
import com.alphadevelopmentsolutions.frcscout.Classes.Team;
import com.alphadevelopmentsolutions.frcscout.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment
{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "teamId";

    private int teamId;

    private OnFragmentInteractionListener mListener;

    public TeamFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param teamId id of team to show
     * @return A new instance of fragment TeamFragment.
     */
    public static TeamFragment newInstance(int teamId)
    {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, teamId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            teamId = getArguments().getInt(ARG_PARAM1);
        }
    }

    private Team team;

    private RecyclerView scoutCardsRecyclerView;

    private TextView teamNumberNameTextView;
    private TextView teamLocationTextView;

    private FontAwesomeIcon facebookFontAwesomeBrandIcon;
    private FontAwesomeIcon twitterFontAwesomeBrandIcon;
    private FontAwesomeIcon instagramFontAwesomeBrandIcon;
    private FontAwesomeIcon youtubeFontAwesomeBrandIcon;
    private FontAwesomeIcon websiteFontAwesomeSolidIcon;

    private FloatingActionButton addMatchFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        final MainActivity context = (MainActivity) getActivity();

        //gets rid of the shadow on the actionbar
        ActionBar actionBar = context.getSupportActionBar();
        actionBar.setElevation(0);

        //load the current team you are viewing
        team = new Team(teamId);
        team.load(context.getDatabase());

        //assign the vars to the views on the page
        teamNumberNameTextView = view.findViewById(R.id.TeamNumberNameTextView);
        teamLocationTextView = view.findViewById(R.id.TeamLocationTextView);

        facebookFontAwesomeBrandIcon = view.findViewById(R.id.FacebookFontAwesomeBrandIcon);
        twitterFontAwesomeBrandIcon = view.findViewById(R.id.TwitterFontAwesomeBrandIcon);
        instagramFontAwesomeBrandIcon = view.findViewById(R.id.InstagramFontAwesomeBrandIcon);
        youtubeFontAwesomeBrandIcon = view.findViewById(R.id.YoutubeFontAwesomeBrandIcon);
        websiteFontAwesomeSolidIcon = view.findViewById(R.id.WebsiteFontAwesomeSolidIcon);

        addMatchFloatingActionButton = view.findViewById(R.id.AddMatchFloatingActionButton);

        //checks to see if the team has a valid URL for each social media, if not hide the icon
        if(team.getFacebookURL() != null && !team.getFacebookURL().equals("")) facebookFontAwesomeBrandIcon.setURL(team.getFacebookURL(), context);
        else facebookFontAwesomeBrandIcon.hide();

        if(team.getTwitterURL() != null && !team.getTwitterURL().equals("")) twitterFontAwesomeBrandIcon.setURL(team.getTwitterURL(), context);
        else twitterFontAwesomeBrandIcon.hide();

        if(team.getInstagramURL() != null && !team.getInstagramURL().equals("")) instagramFontAwesomeBrandIcon.setURL(team.getInstagramURL(), context);
        else instagramFontAwesomeBrandIcon.hide();

        if(team.getYoutubeURL() != null && !team.getYoutubeURL().equals("")) youtubeFontAwesomeBrandIcon.setURL(team.getYoutubeURL(), context);
        else youtubeFontAwesomeBrandIcon.hide();

        if(team.getWebsiteURL() != null && !team.getWebsiteURL().equals("")) websiteFontAwesomeSolidIcon.setURL(team.getWebsiteURL(), context);
        else websiteFontAwesomeSolidIcon.hide();

        //logic for adding a new match
        addMatchFloatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //swap fragments
                FragmentManager fragmentManager = context.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.MainFrame, ScoutCardFragment.newInstance(-1, team.getId()));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        teamNumberNameTextView.setText(team.getId() + " - " + team.getName());
        teamLocationTextView.setText(team.getCity() + ", " + team.getStateProvince() + ", " + team.getCountry());

        //SCOUT CARD GARBAGE
        scoutCardsRecyclerView = view.findViewById(R.id.ScoutCardsRecyclerView);
        
        ScoutCardsRecyclerViewAdapter scoutCardsRecyclerViewAdapter = new ScoutCardsRecyclerViewAdapter(team, context.getDatabase().getScoutCards(team), context);

        scoutCardsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        scoutCardsRecyclerView.setAdapter(scoutCardsRecyclerViewAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
