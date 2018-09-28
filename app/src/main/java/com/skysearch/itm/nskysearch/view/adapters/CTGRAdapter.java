package com.skysearch.itm.nskysearch.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH_CTGR;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.CTGRAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.fragments.ContainerFragment;
import com.skysearch.itm.nskysearch.view.adapters.holders.CTGRViewHolder;

import java.util.ArrayList;

public class CTGRAdapter extends RecyclerView.Adapter<CTGRViewHolder> implements CTGRAdapterContract.Model, CTGRAdapterContract.View{
    public Context context;
    private LayoutInflater mInflator;
    private ArrayList<DTO_CH_CTGR> ctgrs;
    public final String TAG = "CTGRAdapter";

    public OnItemClickListener onItemClickListener;
    public ContainerFragment.ChangeCategoryCallback mCallback;
    public ContainerFragment.ChangeViewCallback mChangeViewCallback;
    public CTGRAdapter(Context context, ContainerFragment.ChangeCategoryCallback callback){
        this.context = context;
        this.mCallback = callback;
    }
    public CTGRAdapter(Context context, ContainerFragment.ChangeViewCallback callback){
        this.context = context;
        this.mChangeViewCallback = callback;
    }



    @NonNull
    @Override
    public CTGRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(context).inflate(R.layout.ctgr_item, parent, false);
        CTGRViewHolder result = new CTGRViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull final CTGRViewHolder holder, final int position) {


        holder.ctgr_name.setText(ctgrs.get(position).getCH_CTGR());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = ctgrs.get(position).getCH_CTGR();
                //ContainerFragment.CHANNEL_CATEGORY category;
                Log.d(TAG, "onClick::type "+type);
                //category = ContainerFragment.CHANNEL_CATEGORY.valueOf(type);
                //Log.d(TAG, "onClick:category "+category);
                // mCallback.onSelectCategory(type);
                ContainerFragment.ViewType viewType;
                viewType = ContainerFragment.ViewType.valueOf("CTGR");
                mChangeViewCallback.changeView(ContainerFragment.ViewType.valueOf("CTGR"), type);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(ctgrs==null){
            return 0;
        }
        return ctgrs.size();
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
    public void addCTGRs(ArrayList<DTO_CH_CTGR> ctgrs) {
        this.ctgrs = ctgrs;
    }

    @Override
    public void clearItem() {
        if(ctgrs!=null){
            ctgrs.clear();
        }
    }

    @Override
    public DTO_CH_CTGR getListingItem(int position) {
        return this.ctgrs.get(position);
    }


}