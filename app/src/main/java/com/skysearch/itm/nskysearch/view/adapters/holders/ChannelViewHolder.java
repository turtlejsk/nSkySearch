package com.skysearch.itm.nskysearch.view.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.channel_image)
    public ImageView channel_image;

    @BindView(R.id.channel_name)
    public TextView channel_name;

    @BindView(R.id.prog_air)
    public TextView prog_air;

    public ChannelViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getProg_air() {
        return prog_air;
    }

    public void setProg_air(TextView prog_air) {
        this.prog_air = prog_air;
    }

    public ImageView getChannel_image() {
        return channel_image;
    }

    public void setChannel_image(ImageView channel_image) {
        this.channel_image = channel_image;
    }

    public TextView getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(TextView channel_name) {
        this.channel_name = channel_name;
    }
}
