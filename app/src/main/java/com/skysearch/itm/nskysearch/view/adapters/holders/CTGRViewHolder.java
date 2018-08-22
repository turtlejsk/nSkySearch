package com.skysearch.itm.nskysearch.view.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CTGRViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ctgr_image)
    public ImageView ctgr_image;

    @BindView(R.id.ctgr_name)
    public TextView ctgr_name;

    public CTGRViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getCtgr_image() {
        return ctgr_image;
    }

    public void setCtgr_image(ImageView ctgr_image) {
        this.ctgr_image = ctgr_image;
    }

    public TextView getCtgr_name() {
        return ctgr_name;
    }

    public void setCtgr_name(TextView ctgr_name) {
        this.ctgr_name = ctgr_name;
    }
}
