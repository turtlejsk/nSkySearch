package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skysearch.itm.nskysearch.Presenter.ChannelContract;
import com.skysearch.itm.nskysearch.Presenter.ChannelPresenter;
import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.CTGRAdapter;
import com.skysearch.itm.nskysearch.view.adapters.ListListingAdapter;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;

public class CTGRFragment extends Fragment implements ChannelContract.View {
    RecyclerView recyclerView;
    ChannelPresenter channelPresenter;
    CTGRAdapter ctgrAdapter;
    public final String TAG = "CTGRFragment";
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static CTGRFragment newInstance(int sectionNumber) {
        CTGRFragment fragment = new CTGRFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        Log.d("Fragment","created");
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
        Log.i(TAG,"onCreateView");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_ctgr, container,false);
        Log.i(TAG,"rootView : "+rootView.toString());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_ctgr);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        Log.i(TAG,"recyclerView : "+recyclerView.toString());
        recyclerView.setAdapter(ctgrAdapter);
        ctgrAdapter = new CTGRAdapter(rootView.getContext());
        channelPresenter = new ChannelPresenter();
        channelPresenter.setListingAdapterModel(ctgrAdapter);
        channelPresenter.setListingAdapterView(ctgrAdapter);
        channelPresenter.attachView(this);
        channelPresenter.loadItems(rootView.getContext(),false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
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
