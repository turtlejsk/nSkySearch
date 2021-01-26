package com.skysearch.itm.nskysearch.view.adapters.holders.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchVODHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.vod_image)
    public ImageView vod_image;

    @BindView(R.id.vod_name)
    public TextView vod_name;

    @BindView(R.id.vod_price)
    public TextView vod_price;

    public SearchVODHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getVod_image() {
        return vod_image;
    }

    public void setVod_image(ImageView vod_image) {
        this.vod_image = vod_image;
    }

    public TextView getVod_name() {
        return vod_name;
    }

    public void setVod_name(String vod_name) {
        this.vod_name.setText(vod_name);
    }

    public TextView getVod_price() {
        return vod_price;
    }

    public void setVod_price(String vod_price) {
        this.vod_price.setText(vod_price);
    }
}
