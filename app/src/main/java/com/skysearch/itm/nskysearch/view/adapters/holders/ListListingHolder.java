package com.skysearch.itm.nskysearch.view.adapters.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ListingItem;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListListingHolder extends ListingHolder{

    @BindView(R.id.prog_image)
    public ImageView programImage;

    @BindView(R.id.title_textview)
    public TextView titleText_now;


    public ListListingHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
