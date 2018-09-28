package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.skysearch.itm.nskysearch.data.DataService;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_PRSN;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.util.DateParser;
import com.skysearch.itm.nskysearch.view.adapters.contracts.SearchAdapterContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter implements SearchContract.Presenter{

    public SearchContract.View view;
    public SearchAdapterContract.Model adapterModel;
    public SearchAdapterContract.View adapterView;

    @Override
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void setListingAdapterModel(SearchAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setListingAdapterView(SearchAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void detachView() {
        if(this.adapterView!=null){
            this.adapterView = null;
        }
    }

    @Override
    public void loadItems(Context context, boolean isClear, String query) {
        getPerson(query);
        getSchd(query);
        //getVOD(query);
        getCh(query);
    }

    /**?
     * 검색할 내용(query)에 해당 되는 편성표 데이터
     * @param query
     */
    public void getSchd(String query){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getSearchSchd(query);
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                ArrayList<DTO_SCHD> channelList = new ArrayList<>();

                Log.d("Test", response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    DTO_SCHD targetChannel = new DTO_SCHD();

                    targetChannel.setSchdId(itemJson.get("SCHD_id").getAsInt());
                    targetChannel.setChId(itemJson.get("CH_id").getAsInt());
                    targetChannel.setEpId(itemJson.get("EP_id").getAsInt());
                    targetChannel.setChannelName(itemJson.get("CH_NAME").getAsString());
                    targetChannel.setActn(itemJson.get("ACTN").getAsString());
                    targetChannel.setTitle(itemJson.get("TITLE").getAsString());
                    targetChannel.setStTime(DateParser.setLocale(itemJson.get("ST_TIME").getAsString()));
                    targetChannel.setEnTime(DateParser.setLocale(itemJson.get("EN_TIME").getAsString()));

                    channelList.add(targetChannel);
                }
                adapterModel.addSchds(channelList);
                Log.d("Presenter","getData");
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });
    }

    /**?
     * 검색할 내용(query)에 해당 되는 VOD 데이터
     * @param query
     */
    public void getVOD(String query){

    }
    /**?
     * 검색할 내용(query)에 해당 되는 인물 데이터
     * @param query
     */
    public void getPerson(String query){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getSearchPrsn(query);
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                ArrayList<DTO_PRSN> personList = new ArrayList<>();
                Log.d("Test", response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    DTO_PRSN targetPrsn = new DTO_PRSN();

                    targetPrsn.setPRSN_NAME(itemJson.get("PRSN_NAME").getAsString());
                    targetPrsn.setPRSN_BIRTH(itemJson.get("PRSN_BIRTH").getAsString());
                    targetPrsn.setPRSN_JOB(itemJson.get("PRSN_JOB").getAsString());
                    targetPrsn.setPRSN_GRP(itemJson.get("PRSN_GRP").getAsString());
                    targetPrsn.setPRSN_PIC(itemJson.get("PRSN_PIC").getAsString());
                    personList.add(targetPrsn);
                }
                adapterModel.addPersons(personList);
                Log.d("Presenter","getData");
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });
    }

    /**?
     * 검색할 내용(query)에 해당 되는 채널 데이터
     * @param query
     */
    public void getCh(String query){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getSearchCh(query);
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                ArrayList<DTO_CH> channelList = new ArrayList<>();

                Log.d("Test", response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    DTO_CH targetChannel = new DTO_CH();

                    targetChannel.setCH_id(itemJson.get("CH_id").getAsInt());
                    targetChannel.setCH_NAME(itemJson.get("CH_NAME").getAsString());
                    targetChannel.setCH_DESCR(itemJson.get("CH_DESCR").getAsString());
                    targetChannel.setCH_CTGR(itemJson.get("CH_CTGR").getAsString());
                    targetChannel.setCH_NUM(itemJson.get("CH_NUM").getAsInt());

                    targetChannel.setsRef(itemJson.get("sRef").getAsInt());
                    channelList.add(targetChannel);
                }
                adapterModel.addChs(channelList);
                Log.d("Presenter","getData");
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });
    }
}
