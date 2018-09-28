package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;

import com.skysearch.itm.nskysearch.view.adapters.contracts.CTGRAdapterContract;

public interface CTGRContract {

    interface View {

        void showToast(String title);
    }

    interface Presenter{

        void attachView(View view);

        void setListingAdapterModel(CTGRAdapterContract.Model adapterModel);

        void setListingAdapterView(CTGRAdapterContract.View adapterView);

        void detachView();

        void loadItems(Context context, boolean isClear);
    }
}
