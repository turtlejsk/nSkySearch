package com.skysearch.itm.nskysearch.data;
import com.google.gson.JsonArray;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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


    @GET("prog/")
    Call<JsonArray> getProgByName(@Query("prog_name") String prog_name);

    @GET("ch/")
    Call<JsonArray> getChannelByChNum(@Query("CH_NUM") int CH_NUM);

    @GET("ch/")
    Call<JsonArray> getChannelByChCtgr(@Query("CH_CTGR") int CH_CTGR);

    @GET("ch/")
    Call<JsonArray> getChannel();

}
