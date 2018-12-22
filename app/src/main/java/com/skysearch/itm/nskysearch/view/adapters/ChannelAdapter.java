package com.skysearch.itm.nskysearch.view.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
    public static final String TAG = "ChannelAdapter";
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
        Log.i(TAG , "onCreateViewHolder: ");
        View view = LayoutInflater.from(context).inflate(R.layout.channel_item, parent, false);
        ChannelViewHolder result = new ChannelViewHolder(view);
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, final int position) {
        String num = String.valueOf(items.get(position).getCH_NUM());
        String ch_name = items.get(position).getCH_NAME();
        Log.d(TAG, "onBindViewHolder: "+num);
        Log.d(TAG, "onBindViewHolder: "+ch_name);

        final String ch_image_url = items.get(position).getCH_DESCR();
        ImageView ch_image = holder.channel_image;
        int hardcode_image = hardcode(ch_name);
        Log.d(TAG, "onBindViewHolder: hardcode_image: "+hardcode_image);

        if(hardcode_image==-1){
            Picasso.get().load(ch_image_url).into(ch_image);
        }else{
            ch_image.setImageResource(hardcode_image);
        }

        String air = items.get(position).getAir();
        holder.channel_name.setText(num+". "+ch_name);
        holder.prog_air.setText(air);




        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int type = items.get(position).getsRef();
                Log.d(TAG, "onClick: "+type);
                mCallback.onSelectChannel(type, ch_image_url);
            }
        });
    }

    public static int hardcode(String ch_name) {
        Log.d(TAG, "hardcode: "+ch_name);
        int result = -1;
        switch (ch_name){
            case "KBS2":
                result = R.drawable.kbs2_live;
                break;
            case "KBS1":
                result =  R.drawable.kbs1_live;
                break;
            case "SBS":
                result =  R.drawable.sbs_live;
                break;
            case "MBC":
                result =  R.drawable.mbc_live;
                break;
            case "채널A":
                result =  R.drawable.channela_live;
                break;
            case "MBN":
                result =  R.drawable.mbn_live;
                break;
            case "JTBC":
                result =  R.drawable.jtbc_live;
                break;
            case "EBS":
                result =  R.drawable.ebs_live;
                break;
        }
        return result;
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
    public void addAirs(ArrayList<DTO_SCHD> airs){
        if (airs != null || airs.size() !=0) {
            this.items.get(airs.size()-count-1).setAir(airs.get(0).getTitle());
            count++;
        }
    }

    @Override
    public void clearItem() {
        this.items = null;
        count = 0;
    }

    @Override
    public DTO_CH getListingItem(int position) {
        return this.items.get(position);
    }

}
