package com.skysearch.itm.nskysearch.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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

public class ListListingActivity extends Activity implements MainContract.View{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ListListingAdapter listingAdapter;
    private GridListingAdapter glisingAdapter;
    private Presenter presenter;
    private StickyRecyclerHeadersDecoration stickyHeaderDecoration;
    public boolean viewType;
    private int progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("listing","start");

        //뷰 바인딩
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewType = false;

        presenter = new Presenter();

        if(viewType){
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
            presenter.attachView(this);
            presenter.setListingAdapterModel(listingAdapter);
            presenter.setListingAdapterView(listingAdapter);

            //레이아웃
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //헤더
            recyclerView.addItemDecoration(stickyHeaderDecoration);
            recyclerView.addItemDecoration(new DividerDecoration(this));
        }else{
            progress= 79;
            glisingAdapter = new GridListingAdapter(this, progress);
            recyclerView.setAdapter(glisingAdapter);
            glisingAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                }
            });
            presenter.attachView(this);
            presenter.setListingAdapterModel(glisingAdapter);
            presenter.setListingAdapterView(glisingAdapter);

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }
        //데이터 로드
        presenter.loadItems(this, false);


    }

    @Override
    public void showToast(String title) {

    }
}
