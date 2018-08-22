package com.skysearch.itm.nskysearch.view.adapters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ExpandParentItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH_CTGR;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.IntroActivity;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.fragments.LLViewPagerFragment;
import com.skysearch.itm.nskysearch.view.adapters.holders.CTGRViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CTGRAdapter extends RecyclerView.Adapter<CTGRViewHolder> implements ChannelAdapterContract.Model, ChannelAdapterContract.View{
    public Context context;
    private LayoutInflater mInflator;
    private ArrayList<DTO_CH> items;
    private ArrayList<DTO_CH_CTGR> ctgrs;
    public final String TAG = "CTGRAdapter";

    public OnItemClickListener onItemClickListener;

    public CTGRAdapter(Context context){
        this.context = context;
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
    public void onBindViewHolder(@NonNull final CTGRViewHolder holder, int position) {


        Log.i(TAG, "onBindViewHolder: "+holder.ctgr_image);
        holder.ctgr_name.setText(ctgrs.get(position).getCH_CTGR());
        holder.ctgr_image.setImageResource(R.drawable.ic_launcher);
        holder.ctgr_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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