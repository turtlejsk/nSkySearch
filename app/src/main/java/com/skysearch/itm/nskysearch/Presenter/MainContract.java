package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;
import android.view.View;

import com.skysearch.itm.nskysearch.view.adapters.contracts.ListingAdapterContract;

public interface MainContract {

    interface View {

        void showToast(String title);

    }

    interface Presenter{

        void attachView(View view);

        void setListingAdapterModel(ListingAdapterContract.Model adapterModel);

        void setListingAdapterView(ListingAdapterContract.View adapterView);

        void detachView();

        void loadItems(Context context, boolean isClear);
    }
}
