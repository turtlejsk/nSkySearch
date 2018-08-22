package com.skysearch.itm.nskysearch.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.view.adapters.BottomBarAdapter;
import com.skysearch.itm.nskysearch.view.adapters.fragments.CTGRFragment;
import com.skysearch.itm.nskysearch.view.adapters.fragments.LLViewPagerFragment;
import com.skysearch.itm.nskysearch.view.adapters.fragments.NoSwipePager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity{
    @BindView(R.id.appbar_default)
    AppBarLayout appBarLayout;
    @BindView(R.id.bottom_navigation)
    public AHBottomNavigation bottomNavigation;
    @BindView(R.id.nsPager)
    public NoSwipePager viewPager;

    BottomBarAdapter pagerAdapter;
    public final String TAG = "IntroActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_frame);
        ButterKnife.bind(this);

        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());
        viewPager.setPagingEnabled(false);
        LLViewPagerFragment llViewPagerFragment1 = LLViewPagerFragment.newInstance(0);
        CTGRFragment ctgrFragment= CTGRFragment.newInstance(1);
        LLViewPagerFragment llViewPagerFragment3 = LLViewPagerFragment.newInstance(2);
        LLViewPagerFragment llViewPagerFragment4 = LLViewPagerFragment.newInstance(3);
        LLViewPagerFragment llViewPagerFragment5 = LLViewPagerFragment.newInstance(4);

        // 그니까, 저 리스트에서 뭐를 누르면 그게 하단 탭으로 갈 수 업슨ㄴ 프레그먼트로 이동한다 이거지? ㅇㅇㅇㅇ
// 하단탭 이동은 안하지? ㅇㅇ
//저 페이지가 담당하는 프레그먼트가 뭐야
        //저 페이지에서 다른페이지로 넘어가는거
        pagerAdapter.addFragments(llViewPagerFragment1);
        pagerAdapter.addFragments(ctgrFragment);
        pagerAdapter.addFragments(llViewPagerFragment3);
        pagerAdapter.addFragments(llViewPagerFragment4);
        pagerAdapter.addFragments(llViewPagerFragment5);
        Log.i(TAG, "onCreate: "+ pagerAdapter.getCount());

        Log.i(TAG, "onCreate: "+ pagerAdapter.getItem(0).getArguments().getInt("section_number"));
        Log.i(TAG, "onCreate: "+ pagerAdapter.getItem(1).getArguments().getInt("section_number"));
        Log.i(TAG, "onCreate: "+ pagerAdapter.getItem(2).getArguments().getInt("section_number"));
        Log.i(TAG, "onCreate: "+ pagerAdapter.getItem(3).getArguments().getInt("section_number"));
        Log.i(TAG, "onCreate: "+ pagerAdapter.getItem(4).getArguments().getInt("section_number"));

        viewPager.setAdapter(pagerAdapter);


        bottomNavigation.setDefaultBackgroundColor(Color.rgb(58,58,60));
        bottomNavigation.setAccentColor(fetchColor(R.color.skylife_red));
        bottomNavigation.setInactiveColor(fetchColor(R.color.skylife_grey));
        //  Enables color Reveal effect
        bottomNavigation.setColored(true);
        // Colors for selected (active) and non-selected items (in color reveal mode).
        bottomNavigation.setColoredModeColors(Color.WHITE,
                fetchColor(R.color.skylife_grey));

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        addBottomNavigationItems();

        bottomNavigation.setCurrentItem(2);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
//                fragment.updateColor(ContextCompat.getColor(MainActivity.this, colors[position]));

                if (!wasSelected)
                    viewPager.setCurrentItem(position);


                return true;
            }
        });

    }
    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }


    private void addBottomNavigationItems() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("검색", R.drawable.ic_launcher);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("편성표", R.drawable.ic_launcher);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Main", R.drawable.ic_launcher);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("VOD", R.drawable.ic_launcher);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("설정", R.drawable.ic_launcher);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
    }


}
