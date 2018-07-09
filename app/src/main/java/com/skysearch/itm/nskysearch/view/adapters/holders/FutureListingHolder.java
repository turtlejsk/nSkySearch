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

    public Button reserveButton;

    @BindView(R.id.prog_image)
    public ImageView programImage;

//    @BindView(R.id.time_textview)
//    public TextView timeText;

    @BindView(R.id.title_textview)
    public TextView titleText;


    public FutureListingHolder(View itemView)  {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
