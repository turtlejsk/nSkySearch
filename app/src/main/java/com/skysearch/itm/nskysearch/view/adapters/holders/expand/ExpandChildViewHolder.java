package com.skysearch.itm.nskysearch.view.adapters.holders.expand;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ExpandChildItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandChildViewHolder extends ChildViewHolder {
    @BindView(R.id.child_ch_id)
    TextView mChildTextView_CH_ID;
    @BindView(R.id.child_ch_name)
    TextView mChildTextView_CH_NAME;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public ExpandChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ExpandChildItem child){
        Log.d("ChildViewHolder",mChildTextView_CH_ID.toString());

        mChildTextView_CH_ID.setText(String.valueOf(child.CH_NUM));
        mChildTextView_CH_NAME.setText(child.CH_NAME);
    }
}
