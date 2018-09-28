package com.skysearch.itm.nskysearch.view.adapters.holders.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skysearch.itm.nskysearch.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchChHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ch_image)
    public ImageView ch_image;

    @BindView(R.id.ch_name)
    public TextView ch_name;

    @BindView(R.id.ch_num)
    public TextView ch_num;

    public SearchChHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getCh_image() {
        return ch_image;
    }

    public void setCh_image(ImageView ch_image) {
        this.ch_image = ch_image;
    }

    public TextView getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name.setText(ch_name);
    }

    public TextView getCh_num() {
        return ch_num;
    }

    public void setCh_num(String ch_num) {
        this.ch_num.setText(ch_num);
    }
}
