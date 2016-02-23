package com.example.rishabh_pc.moodleplus;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link allcourses.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link allcourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class allcourses extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String[]> Param1;


    private OnFragmentInteractionListener mListener;

    public allcourses() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static allcourses newInstance(String[][] param) {

        allcourses fragment = new allcourses();
        Bundle args = new Bundle();
        String tag;
        for (int i=0; i<param.length; i++) {
            tag = "c" + i;
            args.putStringArray(tag, param[i]);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            Param1 = getArguments().getString(ARG_PARAM1);
//
//        }
        Param1 = new ArrayList<>();
            if (getArguments() != null) {
            int i = 0;
            String tag = "c" + i;
            while (getArguments().getStringArray(tag)!=null) {
                Param1.add(getArguments().getStringArray(tag));
                Log.d("each", getArguments().getStringArray(tag).length + "");
                i = i+1;
                tag = "c" + i;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_allcourses, container, false);
        TableLayout table = (TableLayout) v.findViewById(R.id.table1);
        FragmentManager fm = getFragmentManager();

        TableRow.LayoutParams rowparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        for(int i=0; i<Param1.size(); i++) {
            TableRow row = new TableRow(this.getActivity());
            row.setLayoutParams(rowparams);
            TextView tes = new TextView(this.getActivity());
            String[] read1 = Param1.get(i);
            Log.d("here", read1[0] + " " + read1[1]);
            String cour = read1[0].toUpperCase() + ": " + read1[1];
            tes.setText(cour);
            tes.setTextSize(40);
            myOnClickListener clickListener = new myOnClickListener(read1, fm);
            tes.setOnClickListener(clickListener);
            row.addView(tes);

            table.addView(row);

        }
        return v;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
