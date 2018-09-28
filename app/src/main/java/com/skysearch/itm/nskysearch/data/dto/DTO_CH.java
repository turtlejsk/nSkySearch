package com.skysearch.itm.nskysearch.data.dto;

public class DTO_CH {
    int CH_id;
    String CH_NAME;
    int CH_NUM;
    String CH_CTGR;
    String CH_DESCR;
    int sRef;
    String air;

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public int getsRef() {
        return sRef;
    }

    public void setsRef(int sRef) {
        this.sRef = sRef;
    }

    public int getCH_id() {
        return CH_id;
    }

    public void setCH_id(int CH_id) {
        this.CH_id = CH_id;
    }

    public String getCH_NAME() {
        return CH_NAME;
    }

    public void setCH_NAME(String CH_NAME) {
        this.CH_NAME = CH_NAME;
    }

    public int getCH_NUM() {
        return CH_NUM;
    }

    public void setCH_NUM(int CH_NUM) {
        this.CH_NUM = CH_NUM;
    }

    public String getCH_CTGR() {
        return CH_CTGR;
    }

    public void setCH_CTGR(String CH_CTGR) {
        this.CH_CTGR = CH_CTGR;
    }

    public String getCH_DESCR() {
        return CH_DESCR;
    }

    public void setCH_DESCR(String CH_DESCR) {
        this.CH_DESCR = CH_DESCR;
    }
}
