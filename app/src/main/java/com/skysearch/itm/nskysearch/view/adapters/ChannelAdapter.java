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
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.fragments.ContainerFragment;
import com.skysearch.itm.nskysearch.view.adapters.holders.ChannelViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelViewHolder> implements ChannelAdapterContract.View, ChannelAdapterContract.Model {

    public Context context;
    ContainerFragment.ChangeChannelCallback mCallback;
    public final String TAG = "ChannelAdapter";
    public int count = 0;
    public OnItemClickListener onItemClickListener;


    private ArrayList<DTO_CH> items;

    public ChannelAdapter(Context context, ContainerFragment.ChangeChannelCallback callback){
        this.context = context;
        this.mCallback = callback;

    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(context).inflate(R.layout.channel_item, parent, false);
        ChannelViewHolder result = new ChannelViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, final int position) {

        holder.channel_name.setText(items.get(position).getCH_NAME());
        holder.prog_air.setText(items.get(position).getAir());

        final String ch_image_url = items.get(position).getCH_DESCR();
        final ImageView ch_image = holder.channel_image;
        Picasso.get().load(ch_image_url).into(ch_image);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int type = items.get(position).getsRef();
                Log.d(TAG, "onClick: "+type);

                mCallback.onSelectChannel(type,ch_image_url);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(items ==null){
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemSize(){
        if(items ==null){
            return 0;
        }
        return items.size();
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
    public void addAirs(ArrayList<DTO_SCHD> airs ){
        this.items.get(count).setAir(airs.get(0).getTitle());
        count++;
    }

    @Override
    public void clearItem() {
        this.items = null;
    }

    @Override
    public DTO_CH getListingItem(int position) {
        return this.items.get(position);
    }

}
