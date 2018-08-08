package com.skysearch.itm.nskysearch.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.skysearch.itm.nskysearch.Presenter.ChannelContract;
import com.skysearch.itm.nskysearch.Presenter.ChannelPresenter;
import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ExpandChildItem;
import com.skysearch.itm.nskysearch.data.ExpandParentItem;
import com.skysearch.itm.nskysearch.view.adapters.DividerDecoration;
import com.skysearch.itm.nskysearch.view.adapters.ExpandRecyclerAdapter;
import com.skysearch.itm.nskysearch.view.adapters.GridListingAdapter;
import com.skysearch.itm.nskysearch.view.adapters.ListListingAdapter;
import com.skysearch.itm.nskysearch.view.adapters.ListListingViewPagerAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListListingActivity extends AppCompatActivity implements MainContract.View, ChannelContract.View{
//
    @BindView(R.id.channel_view)
    RecyclerView channel_view;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.appbar_listing)
    AppBarLayout appBar;
    @BindView(R.id.container)
    ViewPager mViewPager;


    ExpandRecyclerAdapter expandAdapter;
    //sharedpreference?
    public Presenter mPresenter;
    public ChannelPresenter cPresenter;
    public static boolean viewType = false;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("listing","start");

        //뷰 바인딩
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new Presenter();
        cPresenter = new ChannelPresenter();

        ListListingViewPagerAdapter adapter = new ListListingViewPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    // test
        ExpandChildItem item1 = new ExpandChildItem(1,"beef");
        ExpandChildItem item2 = new ExpandChildItem(2,"cheese");
        ExpandChildItem item3 = new ExpandChildItem(3,"salsa");
        ExpandChildItem item4 = new ExpandChildItem(4,"tortilla");

        ExpandParentItem parent1 = new ExpandParentItem(Arrays.asList(item1,item2,item3));
        parent1.CH_CTGR="Title1";
        ExpandParentItem parent2 = new ExpandParentItem(Arrays.asList(item2,item3,item4));
        parent2.CH_CTGR= "Title2";
        List<ExpandParentItem> parents = Arrays.asList(parent1,parent2);
        expandAdapter = new ExpandRecyclerAdapter(this,parents);

        channel_view.setAdapter(expandAdapter);
        channel_view.setLayoutManager(new LinearLayoutManager(this));
        cPresenter.setListingAdapterModel(expandAdapter);
        cPresenter.setListingAdapterView(expandAdapter);
        cPresenter.attachView(this);
        cPresenter.loadItems(this,false);
/*
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
            initListView();
        }else{
            initGridView();
        }
*/

    }


    public void initChannelView(){
        ExpandChildItem item1 = new ExpandChildItem(1,"beef");
        ExpandChildItem item2 = new ExpandChildItem(2,"cheese");
        ExpandChildItem item3 = new ExpandChildItem(3,"salsa");
        ExpandChildItem item4 = new ExpandChildItem(4,"tortilla");

        ExpandParentItem parent1 = new ExpandParentItem(Arrays.asList(item1,item2,item3));
        parent1.CH_CTGR="Title1";
        ExpandParentItem parent2 = new ExpandParentItem(Arrays.asList(item2,item3,item4));
        parent2.CH_CTGR= "Title2";
        List<ExpandParentItem> parents = Arrays.asList(parent1,parent2);
        expandAdapter = new ExpandRecyclerAdapter(this,parents);
        channel_view.setAdapter(expandAdapter);
    }

    @Override
    public void showToast(String title) {

    }
/*
    public void initGridView(){
        progress= 79;
        glisingAdapter = new GridListingAdapter(this, progress);
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
*/

}
