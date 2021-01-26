package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;

import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;

public interface ChannelContract {
    interface View {

        void showToast(String title);
    }

    interface Presenter{

        void attachView(View view);

        void setListingAdapterModel(ChannelAdapterContract.Model adapterModel);

        void setListingAdapterView(ChannelAdapterContract.View adapterView);

        void detachView();

        void loadItems(Context context, boolean isClear, String ctgr);
    }
}
