package com.skysearch.itm.nskysearch.view.adapters.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastListingHolder extends ListingHolder{

    private Context context;
    private OnItemClickListener onItemClickListener;

    @BindView(R.id.time_textview_past)
    public TextView timeText;

    @BindView(R.id.title_textview_past)
    public TextView titleText;

    public PastListingHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
