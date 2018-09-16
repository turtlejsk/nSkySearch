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
import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.fragments.ContainerFragment;
import com.skysearch.itm.nskysearch.view.adapters.holders.CTGRViewHolder;

import java.util.ArrayList;

public class CTGRAdapter extends RecyclerView.Adapter<CTGRViewHolder> implements ChannelAdapterContract.Model, ChannelAdapterContract.View{
    public Context context;
    private LayoutInflater mInflator;
    private ArrayList<DTO_CH> items;
    private ArrayList<DTO_CH_CTGR> ctgrs;
    public final String TAG = "CTGRAdapter";

    public OnItemClickListener onItemClickListener;
    public ContainerFragment.ChangeCategoryCallback mCallback;

    public CTGRAdapter(Context context, ContainerFragment.ChangeCategoryCallback callback){
        this.context = context;
        this.mCallback = callback;
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
        holder.ctgr_image.setImageResource(R.drawable.skylife);
        holder.ctgr_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "";

                switch (position) {
                    case 0:
                        type = "SAMPLE1";
                        break;
                    case 1:
                        type = "SAMPLE2";
                        break;
                    default:
                        type = "SAMPLE1";
                        break;
                }

                ContainerFragment.CHANNEL_CATEGORY category;
                category = ContainerFragment.CHANNEL_CATEGORY.valueOf(type);

                mCallback.onSelectCategory(category);

            }
        });
    }

    @Override
    public int getItemCount() {
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
    public void addItems(ArrayList<DTO_CH> items) {
        this.items = items;
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
    public DTO_CH getListingItem(int position) {
        return null;
    }


}