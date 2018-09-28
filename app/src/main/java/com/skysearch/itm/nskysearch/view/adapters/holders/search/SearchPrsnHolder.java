package com.skysearch.itm.nskysearch.view.adapters.holders.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPrsnHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.prsn_image)
    public ImageView prsn_image;

    @BindView(R.id.prsn_birth)
    public TextView prsn_birth;

    @BindView(R.id.prsn_name)
    public TextView prsn_name;

    @BindView(R.id.prsn_job)
    public TextView prsn_job;

    public SearchPrsnHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getPrsn_image() {
        return prsn_image;
    }

    public void setPrsn_image(ImageView prsn_image) {
        this.prsn_image = prsn_image;
    }

    public TextView getPrsn_birth() {
        return prsn_birth;
    }

    public void setPrsn_birth(String prsn_birth) {
        this.prsn_birth.setText(prsn_birth);
    }

    public TextView getPrsn_name() {
        return prsn_name;
    }

    public void setPrsn_name(String prsn_name) {
        this.prsn_name.setText(prsn_name);
    }

    public TextView getPrsn_job() {
        return prsn_job;
    }

    public void setPrsn_job(String prsn_job) {
        this.prsn_job.setText(prsn_job);
    }
}
