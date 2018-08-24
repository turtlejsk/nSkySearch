package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.skysearch.itm.nskysearch.R;

public class ContainerFragment extends Fragment {


    private final String TAG ="ContainerFragment";
    public FrameLayout frame;

    public static ContainerFragment newInstance() {
        ContainerFragment fragment = new ContainerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        Log.i(TAG,"binded");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.child_ctgr_fragment,container,false);
        frame = (FrameLayout)rootView.findViewById(R.id.container_ctgr);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Log.i(TAG,"onViewCreated");
        CTGRFragment ctgrFragment = CTGRFragment.newInstance(0);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_ctgr, ctgrFragment).commit();


    }

}
