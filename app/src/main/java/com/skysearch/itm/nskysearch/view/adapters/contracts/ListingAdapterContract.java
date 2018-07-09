package com.skysearch.itm.nskysearch.view.adapters.contracts;

import com.skysearch.itm.nskysearch.data.ListingItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import java.util.ArrayList;

public interface ListingAdapterContract {

    interface View {
        void setOnClickListner(OnItemClickListener clickListner);
        void notifyAdapter();
    }

    interface Model {

        void addItems(ArrayList<DTO_SCHD> items);

        void clearItem();

        DTO_SCHD getItem(int position);
    }
}
