package com.example.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sample.R;
import com.example.sample.data.PagePracelable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private PagePracelable mPagerObj;

    public PagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagerFragment newInstance(PagePracelable pagerObj) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, pagerObj);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPagerObj = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("","mPagerObj.getPage_name():"+mPagerObj.getPage_name());
        View view = null;
        if(mPagerObj.getPage_name().equals("LinearLayout")) {
            view = inflater.inflate(R.layout.fragment_linear_layout, container, false);
            return view;
        }else if(mPagerObj.getPage_name().equals("RelativeLayout")){
            view = inflater.inflate(R.layout.fragment_relative_layout, container, false);
            return view;
        }else if(mPagerObj.getPage_name().equals("TableLayout")){
            view = inflater.inflate(R.layout.fragment_linear_layout, container, false);
            return view;
        }else if(mPagerObj.getPage_name().equals("FrameLayout")){
            view = inflater.inflate(R.layout.fragment_linear_layout, container, false);
            return view;
        }else if(mPagerObj.getPage_name().equals("GridLayout")){
            view = inflater.inflate(R.layout.fragment_linear_layout, container, false);
            return view;
        }
        return view;
    }


}
