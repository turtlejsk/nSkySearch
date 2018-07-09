package com.skysearch.itm.nskysearch.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.ListListingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListListingActivity extends Activity implements MainContract.View{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ListListingAdapter listingAdapter;
    private Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("listing","start");
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        System.out.println(recyclerView);
        listingAdapter = new ListListingAdapter(this);
        recyclerView.setAdapter(listingAdapter);

        presenter = new Presenter();
        presenter.attachView(this);
        presenter.setListingAdapterModel(listingAdapter);
        presenter.setListingAdapterView(listingAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadItems(this, false);
    }

    @Override
    public void showToast(String title) {

    }
}
