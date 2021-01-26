package com.skysearch.itm.nskysearch.data;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class ExpandParentItem implements ParentListItem {
    public String CH_CTGR;

    private List mChilds;

    public ExpandParentItem(){

    }
    public ExpandParentItem(List childs){
        mChilds= childs;
    }
    @Override
    public List<?> getChildItemList() {
        return mChilds;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

}
