package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.skysearch.itm.nskysearch.Presenter.CTGRContract;
import com.skysearch.itm.nskysearch.Presenter.CTGRPresenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.CTGRAdapter;

@SuppressLint("ValidFragment")
public class CTGRFragment extends Fragment implements CTGRContract.View {
    RecyclerView recyclerView;
    CTGRPresenter ctgrPresenter;
    CTGRAdapter ctgrAdapter;

    Toolbar toolbar;
    ContainerFragment.ChangeCategoryCallback mClickCallback;
    ContainerFragment.ChangeViewCallback mChangeViewCallback;
    private final String TAG = "CTGRFragment";

    public static CTGRFragment newInstance(ContainerFragment.ChangeCategoryCallback callback) {
        return new CTGRFragment(callback);
    }

    public CTGRFragment(){
        super();
    }

    public CTGRFragment(ContainerFragment.ChangeCategoryCallback callback) {
        super();
        this.mClickCallback = callback;
    }

    public CTGRFragment(ContainerFragment.ChangeViewCallback callback) {
        super();
        this.mChangeViewCallback = callback;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        Log.i(TAG,"binded");

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_ctgr, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(TAG,"onCreateView");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_ctgr, container,false);

        //toolbar = (Toolbar)rootView.findViewById(R.id.toolbar_ctgr);

        Log.i(TAG,"rootView : "+rootView.toString());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_ctgr);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        Log.i(TAG,"recyclerView : "+recyclerView.toString());
        //ctgrAdapter = new CTGRAdapter(rootView.getContext(), mClickCallback);
        ctgrAdapter = new CTGRAdapter(rootView.getContext(), mChangeViewCallback);
        recyclerView.setAdapter(ctgrAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        ctgrPresenter = new CTGRPresenter();
        ctgrPresenter.setListingAdapterModel(ctgrAdapter);
        ctgrPresenter.setListingAdapterView(ctgrAdapter);
        ctgrPresenter.attachView(this);
        ctgrPresenter.loadItems(rootView.getContext(),false);
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
