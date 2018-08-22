package com.skysearch.itm.nskysearch.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BottomBarAdapter extends SmartFragmentStatePagerAdapter {
    public final String TAG = "BottomBarAdapter";
    private final List<Fragment> fragments = new ArrayList<>();

    public BottomBarAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    // Our custom method that populates this Adapter with Fragments
    public void addFragments(Fragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem: "+position);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}