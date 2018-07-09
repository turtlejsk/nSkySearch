package com.skysearch.itm.nskysearch.view.adapters.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ListingItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import butterknife.BindView;

public class ListingHolder extends RecyclerView.ViewHolder {
    public TextView titleText;
    public OnItemClickListener onItemClickListener;

    public ListingHolder(View itemView) {
        super(itemView);
    }



}
