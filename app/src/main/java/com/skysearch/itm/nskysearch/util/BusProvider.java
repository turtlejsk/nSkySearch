package com.skysearch.itm.nskysearch.util;

import com.squareup.otto.Bus;

public class BusProvider extends Bus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }
    private BusProvider(){

    }
}
