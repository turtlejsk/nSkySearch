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
    public FrameLayout mContainer;

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

        mContainer = rootView.findViewById(R.id.container_ctgr);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        getChildFragmentManager()
                .beginTransaction()
                .replace(mContainer.getId(), new CTGRFragment(new ChangeCategoryCallback() {
                    @Override
                    public void onSelectCategory(CHANNEL_CATEGORY type) {
                        // 타입에 따라 페이지를 바꿔주시면 됩니다.

                        switch (type) {
                            case SAMPLE1:
                                getChildFragmentManager()
                                        .beginTransaction()
//                                .replace(mContainer.getId(),)
                                        .commit();
                                //
                                break;

                            case SAMPLE2:
                                getChildFragmentManager()
                                        .beginTransaction()
//                                .replace(mContainer.getId(),)
                                        .commit();
                                //

                        }
                    }
                }))
                .commit();

    }

}
