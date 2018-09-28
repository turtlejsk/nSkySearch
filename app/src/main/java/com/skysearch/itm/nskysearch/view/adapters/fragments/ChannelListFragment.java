package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.skysearch.itm.nskysearch.Presenter.ChannelContract;
import com.skysearch.itm.nskysearch.Presenter.ChannelPresenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.ChannelAdapter;

import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ChannelListFragment extends Fragment implements ChannelContract.View {

    RecyclerView recyclerView;
    ChannelAdapter channelAdapter;
    ChannelPresenter channelPresenter;
    String ctgr;
    public FrameLayout mContainer;
    private final String TAG = "ChannelListFragment";
    ContainerFragment.ChangeChannelCallback mClickCallback;
    ContainerFragment.ChangeViewCallback mChangeViewCallback;

    public static ChannelListFragment newInstance(ContainerFragment.ChangeChannelCallback callback) {
        return new ChannelListFragment(callback);
    }



    public ChannelListFragment(){
        super();
    }
    public ChannelListFragment(String ctgr){
        super();
        this.ctgr = ctgr;

    }
    public ChannelListFragment(FrameLayout container){
        super();
        this.mContainer = container;

    }
    public ChannelListFragment(ContainerFragment.ChangeChannelCallback callback) {
        super();
        this.mClickCallback = callback;
    }
//    public ChannelListFragment(ContainerFragment.ChangeViewCallback callback) {
//        super();
//        this.mChangeViewCallback = callback;
//    }

    public ChannelListFragment(String ctgr, ContainerFragment.ChangeChannelCallback callback){
        super();
        this.ctgr = ctgr;
        this.mClickCallback = callback;
    }

//    public ChannelListFragment(String ctgr, ContainerFragment.ChangeViewCallback callback){
//        super();
//        this.ctgr = ctgr;
//        this.mChangeViewCallback = callback;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        Log.i(TAG,"binded");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(TAG,"onCreateView");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_ctgr, container,false);
        Log.i(TAG,"rootView : "+rootView.toString());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_ctgr);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        Log.i(TAG,"recyclerView : "+recyclerView.toString());
        channelAdapter = new ChannelAdapter(rootView.getContext(), mClickCallback);
        recyclerView.setAdapter(channelAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        channelPresenter = new ChannelPresenter();
        channelPresenter.setListingAdapterModel(channelAdapter);
        channelPresenter.setListingAdapterView(channelAdapter);
        channelPresenter.attachView(this);
        channelPresenter.loadItems(rootView.getContext(),false, ctgr);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"onViewCreated");

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void showToast(String title) {

    }

}
