package com.skysearch.itm.nskysearch.view.adapters.contracts;

import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import java.util.ArrayList;

public interface ChannelAdapterContract {
    interface View {

        void setOnClickListner(OnItemClickListener clickListner);
        void notifyAdapter();

    }

    interface Model {

        void addItems(ArrayList<DTO_CH> items);
        void addAirs(ArrayList<DTO_SCHD> airs);
        void clearItem();

        DTO_CH getListingItem(int position);
        int getItemSize();
    }
}
