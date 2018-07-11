package com.skysearch.itm.nskysearch.view.adapters.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FutureListingHolder  extends ListingHolder {

    private Context context;
    private OnItemClickListener onItemClickListener;

    @BindView(R.id.reserve_button)
    public Button reserveButton;

    @BindView(R.id.time_textview_future)
    public TextView timeText_future;

    @BindView(R.id.title_textview_future)
    public TextView titleText_future;


    public FutureListingHolder(View itemView)  {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
