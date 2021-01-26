package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.DividerDecoration;
import com.skysearch.itm.nskysearch.view.adapters.ListListingAdapter;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

@SuppressLint("ValidFragment")
public class LLViewPagerFragment extends Fragment implements MainContract.View{



    RecyclerView recyclerView;
    public String num;
    public String url;
    private ListListingAdapter listingAdapter;
    private Presenter mPresenter;
    private StickyRecyclerHeadersDecoration stickyHeaderDecoration;


    public LLViewPagerFragment(){
        super();
    }

    public LLViewPagerFragment(String num, String url){
        super();
        this.num = num;
        this.url = url;
    }

    public static LLViewPagerFragment newInstance(String num, String url) {
        LLViewPagerFragment fragment = new LLViewPagerFragment(num, url);

        Log.d("Fragment","created");
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LLViewPagerFragment","onCreate");
        Log.i("LLViewPagerFragment","binded");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i("LLViewPagerFragment","onCreateView");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_child, container,false);
        Log.i("LLViewPagerFragment","rootView : "+rootView.toString());
        ImageView ch_image = (ImageView)rootView.findViewById(R.id.listing_image);

        if (!url.isEmpty()) {
            Picasso.get().load(url).into(ch_image);
        }
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        Log.i("LLViewPagerFragment","recyclerView : "+recyclerView.toString());
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
        mPresenter.loadItems(rootView.getContext(),false, num);
        recyclerView.addItemDecoration(stickyHeaderDecoration);

        recyclerView.addItemDecoration(new DividerDecoration(rootView.getContext()));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Log.i("LLViewPagerFragment","onViewCreated");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void showToast(String title) {

    }
}
