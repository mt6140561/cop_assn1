package com.example.rishabh_pc.moodleplus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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
 * {@link comments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link comments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class comments extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String thread_id = "param1";
    private static final String comment = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<String[]> mParam2;

    private OnFragmentInteractionListener mListener;

    public comments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1./
//     * @return A new instance of fragment comments.
     */
    // TODO: Rename and change types and number of parameters
    public static comments newInstance(String id, ArrayList<String[]> comm) {
        comments fragment = new comments();
        Bundle args = new Bundle();
        args.putString(thread_id, id);
        String tag;
        for (int i=0; i<comm.size(); i++) {
            tag = "t" + i;
            args.putStringArray(tag, comm.get(i));
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam2 = new ArrayList<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(thread_id);
            int i = 0;
            String tag = "comment"+i;;
            while (getArguments().getStringArray(tag)!=null) {

                mParam2.add(getArguments().getStringArray(tag));
                i = i+1;
                tag = "comment"+i;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_comments, container, false);
        TableLayout table = (TableLayout) v.findViewById(R.id.comments);
        TableRow.LayoutParams lr = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i=0; i<mParam2.size(); i++) {


            for (int j=0; j<mParam2.get(i).length; j++) {
                TableRow row = new TableRow(getActivity());
                TextView te = new TextView(getActivity());
                te.setText(mParam2.get(i)[j]);
                row.setLayoutParams(lr);
                row.addView(te);
                table.addView(row);


            }
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
