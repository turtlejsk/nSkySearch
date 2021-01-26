package com.skysearch.itm.nskysearch.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.DividerDecoration;
import com.skysearch.itm.nskysearch.view.adapters.GridListingAdapter;
import com.skysearch.itm.nskysearch.view.adapters.ListListingAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListListingActivity extends AppCompatActivity implements MainContract.View{

    @BindView(R.id.list_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.appbar_listing)
    AppBarLayout appBar;

    public ListListingAdapter listingAdapter;
    public GridListingAdapter glistingAdapter;
    public StickyRecyclerHeadersDecoration stickyHeaderDecoration;
    public Presenter mPresenter;
    public static boolean viewType = false;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("listing","start");
        //뷰 바인딩
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        mPresenter = new Presenter();
        initListView();
        //mPresenter.loadItems(this, false);
    }

    @Override
    public void showToast(String title) {

    }


    public void initGridView(){
        progress= 79;
        glistingAdapter = new GridListingAdapter(this, progress);
        recyclerView.setAdapter(glistingAdapter);
        glistingAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }
        });
        mPresenter.attachView(this);
        mPresenter.setListingAdapterModel(glistingAdapter);
        mPresenter.setListingAdapterView(glistingAdapter);

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    public void initListView(){
        //정의
        listingAdapter = new ListListingAdapter(this);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //헤더
        recyclerView.addItemDecoration(stickyHeaderDecoration);
        recyclerView.addItemDecoration(new DividerDecoration(this));
    }
}
