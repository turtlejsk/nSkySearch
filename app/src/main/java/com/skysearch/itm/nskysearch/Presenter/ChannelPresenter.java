package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.skysearch.itm.nskysearch.data.DataService;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChannelPresenter implements ChannelContract.Presenter, OnItemClickListener {

    public ChannelContract.View view;
    public ChannelAdapterContract.Model adapterModel;
    public ChannelAdapterContract.View adapterView;
    public final String TAG = "ChannelPresenter";


    @Override
    public void attachView(ChannelContract.View view) {
        this.view= view;

    }

    @Override
    public void setListingAdapterModel(ChannelAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setListingAdapterView(ChannelAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void detachView() {
        this.adapterView = null;
    }

    @Override
    public void loadItems(Context context, boolean isClear, String ctgr) {
        getData(ctgr);



    }

    @Override
    public void onItemClick(int position) {

    }

    public void getData(String ctgr){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);
        Log.d(TAG, "getData: "+ctgr);
        Map<String, String> param = new HashMap<String, String>();
        param.put("ctgr",ctgr);
        Call<JsonArray> req = service.getChannelByChCtgr(param);
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                ArrayList<DTO_CH> channelList = new ArrayList<>();

                Log.d(TAG, response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    DTO_CH targetChannel = new DTO_CH();
                    targetChannel.setCH_id(itemJson.get("CH_id").getAsInt());
                    targetChannel.setCH_NAME(itemJson.get("CH_NAME").getAsString());
                    targetChannel.setCH_NUM(itemJson.get("CH_NUM").getAsInt());
                    targetChannel.setCH_CTGR(itemJson.get("CH_CTGR").getAsString());
                    targetChannel.setCH_DESCR(itemJson.get("CH_DESCR").getAsString());
                    targetChannel.setsRef(itemJson.get("sRef").getAsInt());
                    channelList.add(targetChannel);
                }
                adapterModel.addItems(channelList);
                adapterView.notifyAdapter();
                int size = adapterModel.getItemSize();
                Log.d(TAG, "loadItems: size :"+size);
                for(int i = 0; i<size ; i++){
                    int ch_id = adapterModel.getListingItem(i).getsRef();
                    Log.d(TAG, "loadItems: ch_id"+ ch_id);
                    getAir(ch_id);
                }
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });


    }

    public void getAir(int ch_id){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);
        Log.d(TAG, "getAir: "+ch_id);
        Call<JsonArray> req = service.getChannelAir(ch_id);
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                ArrayList<DTO_SCHD> channelList = new ArrayList<>();

                Log.d(TAG, response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    DTO_SCHD targetChannel = new DTO_SCHD();
                    targetChannel.setTitle(itemJson.get("TITLE").getAsString());

                    channelList.add(targetChannel);
                }
                adapterModel.addAirs(channelList);
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });

    }
}
