package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;

import com.skysearch.itm.nskysearch.view.adapters.contracts.SearchAdapterContract;

public interface SearchContract {

    interface View {

        void showToast(String title);
    }

    interface Presenter{

        void attachView(View view);

        void setListingAdapterModel(SearchAdapterContract.Model adapterModel);

        void setListingAdapterView(SearchAdapterContract.View adapterView);

        void detachView();

        void loadItems(Context context, boolean isClear, String query);
    }
}
