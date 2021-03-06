package com.example.rishabh_pc.moodleplus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link threads.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link threads#newInstance} factory method to
 * create an instance of this fragment.
 */
public class threads extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String[]> mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public threads() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static threads newInstance(ArrayList<String[]> param1, String param2) {
        threads fragment = new threads();
        Bundle args = new Bundle();
        for (int i=0; i<param1.size(); i++) {
            String tag = "t" + i;
            args.putStringArray(tag, param1.get(i));
        }
        args.putString("coursecode", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1 = new ArrayList<>();
        if (getArguments() != null) {
            int i = 0;
            String tag = "t"+i;
            while (getArguments().getStringArray(tag)!=null) {
                mParam1.add(getArguments().getStringArray(tag));
                Log.d("name", getArguments().getStringArray(tag)[0]);
                i = i+1;
                tag = "t"+i;
            }
            mParam2 = getArguments().getString("coursecode");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_threads, container, false);
        TableLayout table = (TableLayout)v.findViewById(R.id.table_thread);
        TableRow.LayoutParams rowp = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i=0; i<mParam1.size(); i++) {
            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(rowp);
            TextView te = new TextView(getActivity());
            te.setText(i+"");
            row.addView(te);

            TextView te2 = new TextView(getActivity());
            te2.setText(mParam1.get(i)[2]);
            row.addView(te2);



            TextView te3 = new TextView(getActivity());
            te3.setText(mParam1.get(i)[4]);
            row.addView(te3);

            table.addView(row);
            threadPartOnClick tpoc = new threadPartOnClick(mParam1.get(i), (MyAct)getActivity());
            row.setOnClickListener(tpoc);
        }
        Button newt = (Button) v.findViewById(R.id.newt);
        newt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.blanklayout, new newThread().newInstance(mParam2))
                        .commit();
            }
        });

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
