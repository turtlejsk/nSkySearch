package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.DividerDecoration;
import com.skysearch.itm.nskysearch.view.adapters.GridListingAdapter;
import com.skysearch.itm.nskysearch.view.adapters.ListListingAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LLViewPagerFragment extends Fragment implements MainContract.View{


    //@BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ListListingAdapter listingAdapter;
    private GridListingAdapter glisingAdapter;
    private int progress;
    private Presenter mPresenter;
    private static boolean viewType = true;
    private StickyRecyclerHeadersDecoration stickyHeaderDecoration;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static LLViewPagerFragment newInstance(int sectionNumber) {
        LLViewPagerFragment fragment = new LLViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        Log.d("Fragment","created");
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment","onCreate");
        Log.i("Fragment","binded");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i("Fragment","onCreateView");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_child, container,false);
        Log.i("Fragment","rootView : "+rootView.toString());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        Log.i("Fragment","recyclerView : "+recyclerView.toString());
        listingAdapter = new ListListingAdapter(rootView.getContext());

        stickyHeaderDecoration = new StickyRecyclerHeadersDecoration(listingAdapter);
        listingAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
            }
        });
        recyclerView.setAdapter(listingAdapter);
        mPresenter = new Presenter();
        mPresenter.attachView(this);
        mPresenter.setListingAdapterView(listingAdapter);
        mPresenter.setListingAdapterModel(listingAdapter);

        recyclerView.addItemDecoration(stickyHeaderDecoration);
        recyclerView.addItemDecoration(new DividerDecoration(rootView.getContext()));
        mPresenter.loadItems(rootView.getContext(),false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Log.i("Fragment","onViewCreated");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    public void initGridView(Context context){
        progress= 79;
        glisingAdapter = new GridListingAdapter(context, progress);
        recyclerView.setAdapter(glisingAdapter);
        glisingAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }
        });
        mPresenter.attachView(this);
        mPresenter.setListingAdapterModel(glisingAdapter);
        mPresenter.setListingAdapterView(glisingAdapter);

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    public void initListView(Context context){
        //정의
        listingAdapter = new ListListingAdapter(context);
        recyclerView.setAdapter(listingAdapter);
        stickyHeaderDecoration = new StickyRecyclerHeadersDecoration(listingAdapter);
        listingAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {

            }
        });
        //MVP
        mPresenter.attachView(this);
        mPresenter.setListingAdapterModel(listingAdapter);
        mPresenter.setListingAdapterView(listingAdapter);

        //레이아웃
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //헤더
        recyclerView.addItemDecoration(stickyHeaderDecoration);
        recyclerView.addItemDecoration(new DividerDecoration(context));
    }


    @Override
    public void showToast(String title) {

    }
}
