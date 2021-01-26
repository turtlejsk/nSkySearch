package com.skysearch.itm.nskysearch.data;

public class ExpandChildItem {
    public int CH_NUM;
    public String CH_NAME;

    public ExpandChildItem(){

    }
    public ExpandChildItem(int CH_NUM, String CH_NAME){
        this.CH_NUM = CH_NUM;
        this.CH_NAME = CH_NAME;

    }

    public int getCH_NUM() {
        return CH_NUM;
    }

    public void setCH_NUM(int CH_NUM) {
        this.CH_NUM = CH_NUM;
    }

    public String getCH_NAME() {
        return CH_NAME;
    }

    public void setCH_NAME(String CH_NAME) {
        this.CH_NAME = CH_NAME;
    }
}
