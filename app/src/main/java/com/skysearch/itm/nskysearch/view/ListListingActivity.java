package com.skysearch.itm.nskysearch.view;

import android.app.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayoutSpringBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.github.mmin18.widget.RealtimeBlurView;
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

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.btn_switch)
    Button btn_switch;
    @BindView(R.id.appbar_listing)
    AppBarLayout appBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ListListingAdapter listingAdapter;
    private GridListingAdapter glisingAdapter;
    private Presenter presenter;
    private StickyRecyclerHeadersDecoration stickyHeaderDecoration;
    //sharedpreference?
    public static boolean viewType = false;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("listing","start");

        //뷰 바인딩
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new Presenter();
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewType = !viewType;
                //viewType 변경
                //새로고침
                recreate();
            }
        });

        //viewType에 따른 레이아웃 전환
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

            //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }
        //데이터 로드
        presenter.loadItems(this, false);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //AppBar

    }

    @Override
    public void showToast(String title) {

    }
}
