package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.skysearch.itm.nskysearch.data.DataService;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH_CTGR;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.CTGRAdapterContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CTGRPresenter implements CTGRContract.Presenter, OnItemClickListener {

    private CTGRContract.View view;
    private CTGRAdapterContract.Model adapterModel;
    private CTGRAdapterContract.View adapterView;
    public final String TAG = "ChannelPresenter";
    @Override
    public void attachView(CTGRContract.View view) {
        this.view= view;

    }

    @Override
    public void setListingAdapterModel(CTGRAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setListingAdapterView(CTGRAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void detachView() {
     this.view = null;
    }

    @Override
    public void loadItems(Context context, boolean isClear) {
        getChCTGR();
        Log.i(TAG, "loadItems");
    }

    @Override
    public void onItemClick(int position) {

    }

    public void getChCTGR(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getChCTGR();
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                ArrayList<DTO_CH_CTGR> channelList = new ArrayList<>();

                Log.d("Test", response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    DTO_CH_CTGR targetChannel = new DTO_CH_CTGR();

                    targetChannel.setCH_CTGR(itemJson.get("CH_CTGR").getAsString());

                    channelList.add(targetChannel);
                }
                adapterModel.addCTGRs(channelList);
                Log.d("Presenter","getData");
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });
    }

    public void getData(String ctgr){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getChannelByChCtgr(ctgr);
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
                    targetChannel.setCH_NUM(itemJson.get("CH_NUM").getAsInt());
                    targetChannel.setCH_CTGR(itemJson.get("CH_CTGR").getAsString());
                    targetChannel.setCH_DESCR(itemJson.get("CH_DESCR").getAsString());

                    channelList.add(targetChannel);
                }
                //adapterModel.addItems(channelList);
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
