package com.skysearch.itm.nskysearch.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.skysearch.itm.nskysearch.data.DataService;
import com.skysearch.itm.nskysearch.data.ListingItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.util.DateParser;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ListingAdapterContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Presenter implements MainContract.Presenter, OnItemClickListener{

    private MainContract.View view;

    private ListingAdapterContract.Model adapterModel;
    private ListingAdapterContract.View adapterView;


    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setListingAdapterModel(ListingAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setListingAdapterView(ListingAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListner(this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadItems(Context context, boolean isClear) {
        getData();

    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getScheduleByCHid(101);
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
                adapterModel.addItems(channelList);

                Log.d("Presenter","getData");
                adapterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        DTO_SCHD item = adapterModel.getItem(position);
        view.showToast(item.getTitle());
    }
}
