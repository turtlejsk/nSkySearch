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

import static com.skysearch.itm.nskysearch.view.adapters.fragments.ContainerFragment.CHANNEL_CATEGORY.CTGR;
import static com.skysearch.itm.nskysearch.view.adapters.fragments.ContainerFragment.CHANNEL_CATEGORY.skyDrama;

public class SearchContainerFragment extends Fragment {


    private final String TAG ="ContainerFragment";
    public FrameLayout mContainer;

    public static SearchContainerFragment newInstance() {
        SearchContainerFragment fragment = new SearchContainerFragment();

        return fragment;
    }

    public SearchContainerFragment(){
        super();
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


        getChildFragmentManager()
                .beginTransaction()
                .replace(mContainer.getId(), new SearchFragment(new ChangeViewCallback(){
                    @Override
                    public void changeView(ViewType type, String value){
                        switch (type) {
                            case CTGR:
                                break;
                            case Channel:
                                getChildFragmentManager().beginTransaction().replace(mContainer.getId(), new LLViewPagerFragment(value,"")).commit();
                                break;
                            case Listing:
                                getChildFragmentManager().beginTransaction().replace(mContainer.getId(), new LLViewPagerFragment(value,"")).commit();
                                break;
                        }
                    }
                }))
                .commit();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }


    public interface ChangeViewCallback{
        void changeView(ViewType type, String value);
    }

    public enum ViewType{
        CTGR("CTGR"),
        Channel("Channel"),
        Listing("Listing");

        String type;
        ViewType(String type){this.type = type;}
    }

}
