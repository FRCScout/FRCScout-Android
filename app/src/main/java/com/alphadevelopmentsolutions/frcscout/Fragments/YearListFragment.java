package com.alphadevelopmentsolutions.frcscout.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphadevelopmentsolutions.frcscout.Adapters.EventListRecyclerViewAdapter;
import com.alphadevelopmentsolutions.frcscout.Adapters.YearListRecyclerViewAdapter;
import com.alphadevelopmentsolutions.frcscout.Classes.Tables.Event;
import com.alphadevelopmentsolutions.frcscout.Classes.Tables.Years;
import com.alphadevelopmentsolutions.frcscout.Interfaces.Constants;
import com.alphadevelopmentsolutions.frcscout.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link YearListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link YearListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YearListFragment extends MasterFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public YearListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment YearListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YearListFragment newInstance() {
        YearListFragment fragment = new YearListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView yearListRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_year_list, container, false);

        context.setTitle(R.string.years);
        context.lockDrawerLayout();

        joinLoadingThread();

        //showing this view means the user has not selected an event or year, clear the shared pref
        context.setPreference(Constants.SharedPrefKeys.SELECTED_EVENT_KEY, -1);
        context.setPreference(Constants.SharedPrefKeys.SELECTED_YEAR_KEY, Calendar.getInstance().get(Calendar.YEAR)); //default to current calendar year

        yearListRecyclerView = view.findViewById(R.id.YearListRecyclerView);

        YearListRecyclerViewAdapter yearListRecyclerViewAdapter = new YearListRecyclerViewAdapter(Years.getYears(null, database), context);
        yearListRecyclerView.setAdapter(yearListRecyclerViewAdapter);
        yearListRecyclerView.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
        context.unlockDrawerLayout();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}