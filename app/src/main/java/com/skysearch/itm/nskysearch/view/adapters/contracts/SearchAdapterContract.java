package com.skysearch.itm.nskysearch.view.adapters.contracts;

import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_PRSN;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.data.dto.DTO_VOD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import java.util.ArrayList;

public interface SearchAdapterContract {
    interface View {
        void setOnClickListner(OnItemClickListener clickListner);
        void notifyAdapter();
    }

    interface Model {

        void addSchds(ArrayList<DTO_SCHD> schds);

        void addPersons(ArrayList<DTO_PRSN> persons);

        void addVOD(ArrayList<DTO_VOD> vods);

        void addChs(ArrayList<DTO_CH> chs);

        void clearItem();

        DTO_SCHD getListingItem(int position);

        DTO_PRSN getPersonItem(int position);

        DTO_VOD getVODItem(int position);

        DTO_CH getChItem(int position);
    }
}
