package com.example.rishabh_pc.moodleplus;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link coursedata.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link coursedata#newInstance} factory method to
 * create an instance of this fragment.
 */
public class coursedata extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String INFO = "array";
  //  private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String[] basics;
   // private String mParam2;

    private OnFragmentInteractionListener mListener;

    public coursedata() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment coursedata.
     */
    // TODO: Rename and change types and number of parameters
    public static coursedata newInstance(String[] param1) {
        coursedata fragment = new coursedata();
        Bundle args = new Bundle();
        args.putStringArray(INFO, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            basics = getArguments().getStringArray(INFO);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_coursedata, container, false);
        TextView te = (TextView) v.findViewById(R.id.coursename);
        te.setText(basics[0]+": "+ basics[1]);
        TextView te2 = (TextView) v.findViewById(R.id.coursecode);
        te2.setText(basics[2]);
//        Button thread = (Button)v.findViewById(R.id.Threads);
//        threadOnClick toc = new threadOnClick(this, getFragmentManager(), v, getActivity().getApplicationContext());
//        thread.setOnClickListener(toc);
        ((MyAct)getActivity()).threadsJSON(this, (Button)v.findViewById(R.id.Threads));


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
