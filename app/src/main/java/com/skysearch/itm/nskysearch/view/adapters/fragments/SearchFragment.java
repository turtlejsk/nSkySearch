package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.skysearch.itm.nskysearch.Presenter.SearchContract;
import com.skysearch.itm.nskysearch.Presenter.SearchPresenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.SearchAdapter;

public class SearchFragment extends Fragment implements SearchContract.View, SearchView.OnQueryTextListener{
//        @BindView(R.id.appbar_default)
   // public AppBarLayout appBarLayout;
    //public Toolbar toolbar;
    public Context mContext;
    public RecyclerView recyclerView;
    private SearchPresenter mPresenter;
    private SearchAdapter searchAdapter;
    public SearchContainerFragment.ChangeViewCallback mChangeViewCallback;
    public SearchView searchView;
    private static final String TAG = "SearchFragment";

    public SearchFragment(){
        super();
    }

    @SuppressLint("ValidFragment")
    public SearchFragment(SearchContainerFragment.ChangeViewCallback callback){
        super();
        mChangeViewCallback = callback;
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Log.d("Fragment","created");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_search, container,false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_search);
        //appBarLayout = (AppBarLayout)rootView.findViewById(R.id.appbar_search);
       // toolbar = (Toolbar)rootView.findViewById(R.id.toolbar_search);
        mContext = rootView.getContext();

        searchView = (SearchView)rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        searchAdapter = new SearchAdapter(rootView.getContext(), mChangeViewCallback);

        recyclerView.setAdapter(searchAdapter);

        mPresenter = new SearchPresenter();
        mPresenter.attachView(this);
        mPresenter.setListingAdapterModel(searchAdapter);
        mPresenter.setListingAdapterView(searchAdapter);

        //mPresenter.loadItems();
        return rootView;
    }
//
//    @Override
//    public void onPrepareOptionsMenu(Menu menu){
//        Log.d(TAG,"menu: "+menu);
//        MenuItem mSearchMenuItem = menu.findItem(R.id.search_menu);
//        Log.d(TAG,"mSearchMenuItem: "+mSearchMenuItem);
//        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void showToast(String title) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() > 1){
            mPresenter.loadItems(mContext,false,newText);
        }
        return true;
    }
}
