package com.skysearch.itm.nskysearch.view.adapters.holders.expand;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ExpandParentItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandParentViewHolder extends ParentViewHolder  {
    @BindView(R.id.parent_ch_ctgr)
    TextView mParentTextView;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public ExpandParentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ExpandParentItem parent){
        mParentTextView.setText(parent.CH_CTGR);
    }
}
