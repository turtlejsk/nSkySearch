package com.skysearch.itm.nskysearch.view.adapters.holders.grid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.grid_image)
    public ImageView gridImage;

    @BindView(R.id.grid_channel_textview)
    public TextView tv_channel_grid;

    @BindView(R.id.grid_title_textview)
    public TextView tv_title_grid;
    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    public GridHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
