package com.skysearch.itm.nskysearch.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_PRSN;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.data.dto.DTO_VOD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.SearchAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.fragments.SearchContainerFragment;
import com.skysearch.itm.nskysearch.view.adapters.holders.search.SearchChHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.search.SearchPrsnHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.search.SearchSCHDHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.search.SearchVODHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SearchAdapterContract.View, SearchAdapterContract.Model{

    public Context mContext;
    public SearchContainerFragment.ChangeViewCallback mChangeViewCallback;

    public final String TAG = "SearchAdapter";

    public OnItemClickListener onItemClickListener;

    public ArrayList<DTO_PRSN> prsns;
    public ArrayList<DTO_SCHD> schds;
    public ArrayList<DTO_CH> chs;
    public ArrayList<DTO_VOD> vods;

    public int chs_size;
    public int schds_size;
    public int prsns_size;
    public int vods_size;

    public int total_size;

    private static final int VH_PERSON = 0;
    private static final int VH_SCHD = 1;
    private static final int VH_CH = 2;
    private static final int VH_VOD = 3;


    public SearchAdapter(Context context, SearchContainerFragment.ChangeViewCallback callback){
        mContext = context;
        mChangeViewCallback = callback;
        prsns = new ArrayList<DTO_PRSN>();
        schds = new ArrayList<DTO_SCHD>();
        chs = new ArrayList<DTO_CH>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case VH_PERSON:
                view = LayoutInflater.from(mContext).inflate(R.layout.search_prsn, parent, false);
                viewHolder = new SearchPrsnHolder(view);
                break;
            case VH_SCHD:
                view = LayoutInflater.from(mContext).inflate(R.layout.search_schd, parent, false);
                viewHolder = new SearchSCHDHolder(view);
                break;
            case VH_CH:
                view = LayoutInflater.from(mContext).inflate(R.layout.search_ch, parent, false);
                viewHolder = new SearchChHolder(view);
                break;
            case VH_VOD:
                view = LayoutInflater.from(mContext).inflate(R.layout.search_vod, parent, false);

                viewHolder = new SearchVODHolder(view);

                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int type = -1;
        int index = -1;
        if(getItemViewType(position) == VH_PERSON ){
            index = position;
            DTO_PRSN target = prsns.get(index);
            final SearchPrsnHolder searchPrsnHolder = (SearchPrsnHolder)holder;

            String prsn_birth = target.getPRSN_BIRTH();
            String prsn_job = target.getPRSN_JOB();
            String prsn_name = target.getPRSN_NAME();
            String prsn_image_url = target.getPRSN_PIC();
            //Image Bind

            searchPrsnHolder.setPrsn_birth("생년월일 : "+prsn_birth);
            searchPrsnHolder.setPrsn_job("직업 : "+prsn_job);
            searchPrsnHolder.setPrsn_name("이름 : "+prsn_name);
            ImageView prsn_image = searchPrsnHolder.getPrsn_image();
            Picasso.get().load(prsn_image_url).into(prsn_image);


        }else if(getItemViewType(position) == VH_CH){
            index = position - prsns_size;
            DTO_CH target = chs.get(index);
            final SearchChHolder searchChHolder = (SearchChHolder)holder;

            String ch_image_url = target.getCH_DESCR();
            String ch_name = target.getCH_NAME();
            String ch_num = String.valueOf(target.getCH_NUM());
            ImageView ch_image = searchChHolder.getCh_image();
            Picasso.get().load(ch_image_url).into(ch_image);
            //Image bind

            searchChHolder.setCh_name(ch_name);
            searchChHolder.setCh_num(ch_num);


            //Image bind

        }else if(getItemViewType(position) == VH_SCHD){
            index = position - prsns_size - chs_size;
            DTO_SCHD target = schds.get(index);
            final SearchSCHDHolder searchSCHDHolder = (SearchSCHDHolder)holder;

            String ch_name = target.getChannelName();
            String ch_num = String.valueOf(target.getChId());
            String schd_name = target.getTitle();
            //Image binde

            searchSCHDHolder.setSchd_ch_name(ch_name);
            searchSCHDHolder.setSchd_ch_num(ch_num);
            searchSCHDHolder.setSchd_name(schd_name);
            //searchSCHDHolder.setSchd_image();

        }else if(getItemViewType(position) == VH_VOD){
            index = position - prsns_size - chs_size - schds_size;
            DTO_VOD target = vods.get(index);
            final SearchVODHolder searchVODHolder = (SearchVODHolder)holder;

            String vod_name = target.VOD_TITLE;

            searchVODHolder.setVod_name(vod_name);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = getItemViewType(position);
                SearchContainerFragment.ViewType viewType = null;
                switch (type){
                    case VH_PERSON:

                        break;
                    case VH_SCHD:
                        Log.d(TAG, "onClick::type "+type);
                        viewType = SearchContainerFragment.ViewType.valueOf("Listing");
                        mChangeViewCallback.changeView(viewType, String.valueOf(getListingItem(position).getChId()));
                        break;
                    case VH_CH:
                        Log.d(TAG, "onClick::type "+type);

                        viewType = SearchContainerFragment.ViewType.valueOf("Channel");
                        Log.d(TAG, "onClick: sRef "+String.valueOf(getChItem(position).getsRef()));
                        mChangeViewCallback.changeView(viewType, String.valueOf(getChItem(position).getsRef()));
                        break;
                    case VH_VOD:

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        if(this.chs == null){
            this.chs_size = 0;
        }else{
            this.chs_size = this.chs.size();
        }

        if(this.schds == null){
            this.schds_size = 0;
        }else{
            this.schds_size = this.schds.size();
        }

        if(this.prsns == null){
            this.prsns_size = 0;
        }else{
            this.prsns_size = this.prsns.size();
        }

        if(this.vods == null){
            this.vods_size = 0;
        }else{
            this.vods_size = vods.size();
        }

        this.total_size = this.chs_size + this.schds_size + this.prsns_size + this.vods_size;

        return this.total_size;
    }

    @Override
    public int getItemViewType(int position){
        if(position < prsns_size){
           return VH_PERSON;
        }else if( 0 <= (position - prsns_size) && (position-prsns_size) < chs_size){
            return VH_CH;
        }else if( 0 <= (position - prsns_size - chs_size) && (position - prsns_size - chs_size)< schds_size){
            return VH_SCHD;
        }else if( 0 <= (position - prsns_size - chs_size - schds_size) && (position - prsns_size - chs_size - schds_size) < vods_size){
            return VH_VOD;
        }else {
            return -1;
        }
    }

    @Override
    public void setOnClickListner(OnItemClickListener clickListner) {
        this.onItemClickListener = clickListner;

    }


    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void addSchds(ArrayList<DTO_SCHD> schds) {
        this.schds = schds;
    }

    @Override
    public void addPersons(ArrayList<DTO_PRSN> persons) {
        this.prsns = persons;
    }

    @Override
    public void addVOD(ArrayList<DTO_VOD> vods) {
        this.vods = vods;
    }

    @Override
    public void addChs(ArrayList<DTO_CH> chs){
        this.chs = chs;
    }

    @Override
    public void clearItem() {
        this.schds = null;
        this.prsns = null;
        this.vods = null;
        this.chs = null;
    }

    @Override
    public DTO_PRSN getPersonItem(int position) {
        int index = -1;
        if(position<prsns_size){
            index = position;
        }
        return prsns.get(index);
    }


    @Override
    public DTO_CH getChItem(int position){
        int index = -1;
        if( 0 <= (position - prsns_size) && (position-prsns_size) < chs_size){
            index = position - prsns_size;
        }
        return chs.get(index);
    }

    @Override
    public DTO_SCHD getListingItem(int position) {
        int index = -1;
        if( 0 <= (position - prsns_size-chs_size) && (position-prsns_size-chs_size) < schds_size){
            index = position - prsns_size - chs_size;
        }
        return schds.get(index);
    }

    @Override
    public DTO_VOD getVODItem(int position) {
        int index = -1;
        if( 0 <= (position - prsns_size - chs_size - schds_size) && (position - prsns_size - chs_size - schds_size) < vods_size){
            index = position - prsns_size - chs_size - schds_size;
        }
        return vods.get(index);
    }

    public OnItemClickListener defineListener(){
        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int type = getItemViewType(position);
                SearchContainerFragment.ViewType viewType;
                switch (type){
                    case VH_PERSON:

                        break;
                    case VH_SCHD:
                        Log.d(TAG, "onClick::type "+type);
                        viewType = SearchContainerFragment.ViewType.valueOf("Listing");
                        mChangeViewCallback.changeView(viewType, String.valueOf(getListingItem(position).getChId()));
                        break;
                    case VH_CH:
                        Log.d(TAG, "onClick::type "+type);

                        viewType = SearchContainerFragment.ViewType.valueOf("Channel");
                        mChangeViewCallback.changeView(viewType, String.valueOf(getListingItem(position).getChId()));
                        break;
                    case VH_VOD:

                        break;
                }



            }
        };
        return listener;
    }

}
