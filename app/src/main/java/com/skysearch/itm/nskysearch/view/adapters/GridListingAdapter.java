package com.skysearch.itm.nskysearch.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ListingAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.holders.grid.GridHolder;

import java.util.ArrayList;

public class GridListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ListingAdapterContract.Model, ListingAdapterContract.View{

    public ArrayList<DTO_SCHD> items = new ArrayList<DTO_SCHD>();
    public Context context;
    public OnItemClickListener onItemClickListener;
    private int progress;
    private int lastPosition = -1; // Allows to remember the last item shown on screen

    public GridListingAdapter(Context context, int progress){
        this.context = context;
        this.progress = progress;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder result= null;
        View view = null;


        view = LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false);
        result = new GridHolder(view);
        return result;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GridHolder grid_holder = (GridHolder)holder;
        grid_holder.gridImage.setImageResource(R.drawable.program_image);
        grid_holder.tv_channel_grid.setText("Test Channel");
        grid_holder.tv_title_grid.setText("Test Title");
        grid_holder.progressBar.setProgress(progress);
        setAnimation(grid_holder.gridImage,position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setOnClickListner(OnItemClickListener clickListner) {
        this.onItemClickListener = clickListner;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
        Log.d("GridListingAdapter","notifyAdapter");
    }

    @Override
    public void addItems(ArrayList<DTO_SCHD> items) {
        Log.d("ListListingAdapter","addItems");
        this.items = items;
    }

    @Override
    public void clearItem() {
        if(items!=null){
            items.clear();
        }
    }

    @Override
    public DTO_SCHD getListingItem(int position) {
        return items.get(position);
    }

    private void setAnimation(View viewToAnimate, int position) { // 새로 보여지는 뷰라면 애니메이션을 해줍니다
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
