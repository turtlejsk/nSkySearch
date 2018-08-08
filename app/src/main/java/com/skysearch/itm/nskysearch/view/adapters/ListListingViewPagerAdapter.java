package com.skysearch.itm.nskysearch.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.skysearch.itm.nskysearch.view.adapters.fragments.LLViewPagerFragment;


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


}
