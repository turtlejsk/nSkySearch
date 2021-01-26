package com.skysearch.itm.nskysearch.view.adapters.holders.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchSCHDHolder extends RecyclerView.ViewHolder {

    //@BindView(R.id.schd_image)
    public ImageView schd_image;

    @BindView(R.id.schd_name)
    public TextView schd_name;

    @BindView(R.id.schd_ch_name)
    public TextView schd_ch_name;

    @BindView(R.id.schd_ch_num)
    public TextView schd_ch_num;


    public SearchSCHDHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getSchd_image() {
        return schd_image;
    }

    public void setSchd_image(ImageView schd_image) {
        this.schd_image = schd_image;
    }

    public TextView getSchd_name() {
        return schd_name;
    }

    public void setSchd_name(String schd_name) {
        this.schd_name.setText(schd_name);
    }

    public TextView getSchd_ch_name() {
        return schd_ch_name;
    }

    public void setSchd_ch_name(String schd_ch_name) {
        this.schd_ch_name.setText(schd_ch_name);
    }

    public TextView getSchd_ch_num() {
        return schd_ch_num;
    }

    public void setSchd_ch_num(String schd_ch_num) {
        this.schd_ch_num.setText(schd_ch_num);
    }
}
