package com.skysearch.itm.nskysearch.data;
import com.google.gson.JsonArray;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DataService {
    //schd Table

    /*
       CH_id
     * return 해당 채널 3일치 편성표 데이터
    * */
    @GET("schd/")
    Call<JsonArray> getScheduleByCHid(@Query("CH_id") int CH_id);

    /**
     *
     * @param title
     * @return title이 포함된 편성표 데이터
     */
    @GET("schd/")
    Call<JsonArray> getScheduleByTitle(@Query("Title") String title);

    @GET("schd/")
    Call<JsonArray> getScheduleByChname(@Query("ch_name") String ch_name);

    @GET("schd/")
    Call<JsonArray> getScheduleByChnum(@Query("ch_num") int ch_num);


    @GET("prog/")
    Call<JsonArray> getProgByName(@Query("prog_name") String prog_name);

    @GET("ch/")
    Call<JsonArray> getChannelByChNum(@Query("CH_num") int CH_NUM);

    @GET("/ch_list")
    Call<JsonArray> getChannelByChCtgr(@QueryMap Map<String, String> ctgr);

    @GET("/ch_air")
    Call<JsonArray> getChannelAir(@Query("ch_id") int CH_id);

    @GET("ch/")
    Call<JsonArray> getChannel();

    @GET("ch_ctgr/")
    Call<JsonArray> getChCTGR();

    @GET("sRef/")
    Call<JsonArray> getsRef(@Query("ch_name") String ch_name);

    @GET("/search_schd")
    Call<JsonArray> getSearchSchd(@Query("keyword") String keyword);
    @GET("/search_prsn")
    Call<JsonArray> getSearchPrsn(@Query("keyword") String keyword);
    @GET("/search_ch")
    Call<JsonArray> getSearchCh(@Query("keyword") String keyword);
}
