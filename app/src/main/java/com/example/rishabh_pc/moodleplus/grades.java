package com.example.rishabh_pc.moodleplus;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link grades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link grades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class grades extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HorizontalScrollView mHorizontalScroll;

    private ArrayList<String[]> Param1;
    private OnFragmentInteractionListener mListener;

    public grades() {
        // Required empty public constructor
    }
    LinearLayout mLinearLayout;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Grades.
     */
    // TODO: Rename and change types and number of parameters
    public static grades newInstance(String[][] param) {

        grades fragment = new grades();
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
          setRetainInstance(true);
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

    private void autoScroll() {
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        int widthScreen = 888;

 int widthContainer = 1000;
// Width of one child (in this case Button)
  int widthChild =1000;
// Nb children which has contained in screen device
  int nbChildInScreen = 1000;
// Width total of the space outside the screen / 2 (= left position)
 int positionLeftWidth = 1000;
mHorizontalScroll.smoothScrollTo(positionLeftWidth, 0);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grades, container, false);
        TableLayout table = (TableLayout) v.findViewById(R.id.table2);

        mHorizontalScroll = new HorizontalScrollView(getActivity());

        mLinearLayout = (LinearLayout) v.findViewById(R.id.blanklayout);

        v.post(new Runnable() {

            @Override
            public void run() {
                autoScroll();}});


        TableRow.LayoutParams rowparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        for(int i=0; i<Param1.size(); i++) {
            TableRow row = new TableRow(this.getActivity());
            row.setLayoutParams(rowparams);
            TextView tes = new TextView(this.getActivity());

            String[] read1 = Param1.get(i);
            Log.d("here", read1[0] + "      " + read1[1]);
            String cour = read1[0].toUpperCase() + "     " + read1[1] + "     "+ read1[2] +"     " +read1[3] +"      "
                    +read1[4]+"       "+ read1[5]+"  ";
            tes.setText(cour);
            tes.setTextSize(18);
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
