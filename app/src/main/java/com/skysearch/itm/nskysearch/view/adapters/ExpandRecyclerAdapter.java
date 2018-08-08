package com.skysearch.itm.nskysearch.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.DataService;
import com.skysearch.itm.nskysearch.data.ExpandChildItem;
import com.skysearch.itm.nskysearch.data.ExpandParentItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH;
import com.skysearch.itm.nskysearch.data.dto.DTO_CH_CTGR;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ChannelAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.holders.expand.ExpandChildViewHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.expand.ExpandParentViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpandRecyclerAdapter extends ExpandableRecyclerAdapter<ExpandParentViewHolder, ExpandChildViewHolder> implements ChannelAdapterContract.Model, ChannelAdapterContract.View{
    private LayoutInflater mInflator;
    private ArrayList<DTO_CH> items;
    private ArrayList<DTO_CH_CTGR> ctgrs;
    List<ExpandParentItem> parentListItems;

    public ExpandRecyclerAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }
    // onCreate ...
    @Override
    public ExpandParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View parentView = mInflator.inflate(R.layout.exp_group, parentViewGroup, false);
        return new ExpandParentViewHolder(parentView);
    }

    @Override
    public ExpandChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View childView = mInflator.inflate(R.layout.exp_item, childViewGroup, false);
        return new ExpandChildViewHolder(childView);
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(ExpandParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ExpandParentItem parent = (ExpandParentItem) parentListItem;
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(ExpandChildViewHolder childViewHolder, int position, Object childListItem) {
        ExpandChildItem child = (ExpandChildItem) childListItem;
        childViewHolder.bind(child);
    }

    @Override
    public void setOnClickListner(OnItemClickListener clickListner) {

    }

    @Override
    public void notifyAdapter() {

    }

    @Override
    public void addItems(ArrayList<DTO_CH> items) {
        this.items = items;

    }

    @Override
    public void addCTGRs(ArrayList<DTO_CH_CTGR> ctgrs) {
        this.ctgrs = ctgrs;
    }

    @Override
    public void clearItem() {

    }

    @Override
    public DTO_CH getListingItem(int position) {
        return null;
    }

    public void getChCTGR(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getChannel();
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

                    ctgrs.add(targetChannel);
                }
                Log.d("Presenter","getData");
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });
    }

    public void getData(final String ctgr){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://211.211.54.158:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        DataService service = retrofit.create(DataService.class);

        Call<JsonArray> req = service.getChannelByChCtgr(ctgr);
        req.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                ArrayList<DTO_CH> channelList = new ArrayList<>();
                ArrayList<ExpandChildItem> childItems = new ArrayList<>();
                Log.d("Test", response.body().toString());
                JsonArray mList = response.body();

                for (JsonElement item : mList) {
                    JsonObject itemJson = item.getAsJsonObject();

                    ExpandChildItem childItem = new ExpandChildItem();
                    childItem.setCH_NAME(itemJson.get("CH_NAME").getAsString());
                    childItem.setCH_NUM(itemJson.get("CH_NUM").getAsInt());

                    DTO_CH targetChannel = new DTO_CH();
                    targetChannel.setCH_id(itemJson.get("CH_id").getAsInt());
                    targetChannel.setCH_NAME(itemJson.get("CH_NAME").getAsString());
                    targetChannel.setCH_NUM(itemJson.get("CH_NUM").getAsInt());
                    targetChannel.setCH_CTGR(itemJson.get("CH_CTGR").getAsString());
                    targetChannel.setCH_DESCR(itemJson.get("CH_DESCR").getAsString());
                    childItems.add(childItem);
                    channelList.add(targetChannel);


                }
                ExpandParentItem parentListItem = new ExpandParentItem(childItems);
                parentListItem.CH_CTGR = ctgr;
                parentListItems.add(parentListItem);
                Log.d("Presenter","getData");
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Presenter","getData::Error", t);
            }
        });
    }
}