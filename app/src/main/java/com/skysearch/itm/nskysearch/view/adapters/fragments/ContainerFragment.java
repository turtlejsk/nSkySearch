package com.skysearch.itm.nskysearch.view.adapters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.skysearch.itm.nskysearch.R;

import butterknife.ButterKnife;

import static com.skysearch.itm.nskysearch.view.adapters.fragments.ContainerFragment.CHANNEL_CATEGORY.skyDrama;

public class ContainerFragment extends Fragment {


    private final String TAG ="ContainerFragment";
    public FrameLayout mContainer;

    public static ContainerFragment newInstance() {
        ContainerFragment fragment = new ContainerFragment();

        return fragment;
    }

    public ContainerFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        Log.i(TAG,"binded");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.child_ctgr_fragment,container,false);

        mContainer = rootView.findViewById(R.id.container_ctgr);



        getChildFragmentManager().beginTransaction().replace(mContainer.getId(), new CTGRFragment(new ChangeViewCallback(){
            @Override
            public void changeView(ViewType type, String value) {
                switch (type){
                    case CTGR:
                        getChildFragmentManager().beginTransaction().replace(mContainer.getId(), new ChannelListFragment(value, new ChangeChannelCallback() {
                            @Override
                            public void onSelectChannel(int ch_num, String url) {
                                    getChildFragmentManager().beginTransaction().replace(mContainer.getId(), new LLViewPagerFragment(String.valueOf(ch_num),url)).commit();
                            }
                        })).commit();

                        break;

                    case Channel:

                        break;

                    case Listing:

                        break;
                }
            }
        })).commit();


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }

    public interface ChangeCategoryCallback {
        void onSelectCategory(CHANNEL_CATEGORY type);
    }

    public interface ChangeChannelCallback {
        void onSelectChannel(int ch_num, String url);
    }

    public interface ChangeViewCallback{
        void changeView(ViewType type, String value);


    }

    public enum ViewType{
        CTGR("CTGR"),
        Channel("Channel"),
        Listing("Listing");

        String type;
        ViewType(String type){this.type = type;}
    }

    public enum CHANNEL_NUM{

        MBC("11"),
        KBS("402"),
        SBS("5"),
        skyDrama("skyDrama");

        String num;
        CHANNEL_NUM (String num){
            this.num =num;
        }
    }

    public enum CHANNEL_CATEGORY {
        CTGR("CTGR"),
        skyTV("skyTV"),
        UHD("UHD"),
        ECON("경제/보도"),
        PUBLIC("공공/공익/준공익"),
        DOCU("교양/정보/다큐"),
        DRAMA("드라마"),
        LIFE("생활/레저/취미"),
        SPORTS("스포츠"),
        KIDS("어린이/만화/교육"),
        _ALL("ALL"),
        MBC("11"),
        KBS("402"),
        SBS("5"),
        skyDrama("35")
        ;

        String type;

        CHANNEL_CATEGORY(String type) {
            this.type = type;
        }
    }

}
