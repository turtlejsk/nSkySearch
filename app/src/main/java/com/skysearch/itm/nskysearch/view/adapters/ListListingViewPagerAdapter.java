package com.skysearch.itm.nskysearch.view.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.skysearch.itm.nskysearch.Presenter.MainContract;
import com.skysearch.itm.nskysearch.Presenter.Presenter;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ListingAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.fragments.LLViewPagerFragment;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.UltraViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListListingViewPagerAdapter extends FragmentPagerAdapter {


    public ListListingViewPagerAdapter(FragmentManager fm){
        super(fm);//
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position){
        Log.d("VPAdapter","getItem("+position+")");
        return LLViewPagerFragment.newInstance(position);
    }

//    @Override
//    public Object instantiateItem(View pager, int position){
//        Log.d("VPAdapter","InstantiateItem");
//        RelativeLayout view  = (RelativeLayout) LayoutInflater.from(pager.getContext()).inflate(R.layout.pager_child, null);
//        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
//        Log.d("VPAdapter",recyclerView.toString());
////        listingAdapter = new ListListingAdapter(recyclerView.getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
////        recyclerView.setAdapter(listingAdapter);
//        Log.d("VPAdapter","findViewById");
//        ((ViewPager) pager).addView(view);
//
//        Log.d("VPAdapter","addView");wka
//        return view;
//    }

}
