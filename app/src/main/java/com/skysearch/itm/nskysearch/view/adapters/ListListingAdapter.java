package com.skysearch.itm.nskysearch.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ListingItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.util.DateParser;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ListingAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.holders.FutureListingHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.HeaderHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.ListListingHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.ListingHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.PastListingHolder;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

public class ListListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ListingAdapterContract.Model, ListingAdapterContract.View, StickyRecyclerHeadersAdapter<HeaderHolder> {

    public ArrayList<DTO_SCHD> items = new ArrayList<DTO_SCHD>();
    public Context context;
    public OnItemClickListener onItemClickListener;
    private int[] mSectionIndices;
    private String[] mSectionDates;
    private static final int ITEM_VIEW_TYPE_FUTURE = 0;
    private static final int ITEM_VIEW_TYPE_NOW = 1;
    private static final int ITEM_VIEW_TYPE_PAST = 2;

    public ListListingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if() 문으로 시간대별로 다른 뷰 생성

        View view;
        RecyclerView.ViewHolder result =null;
        Log.d(TAG, "onCreateViewHolder::position : "+parent.getChildCount());
        Log.d(TAG, "onCreateViewHolder::type : "+viewType);
        switch (viewType){
            case ITEM_VIEW_TYPE_FUTURE:
                view = LayoutInflater.from(context).inflate(R.layout.list_item_future, parent,false);
                result = new FutureListingHolder(view);
                break;
            case ITEM_VIEW_TYPE_NOW:
               view = LayoutInflater.from(context).inflate(R.layout.list_item_now, parent,false);
               result =  new ListListingHolder(view);
               break;
            case ITEM_VIEW_TYPE_PAST:
                view = LayoutInflater.from(context).inflate(R.layout.list_item_past, parent,false);
                result =  new PastListingHolder(view);
                break;

        }
        Log.d("ListListingAdapter","onCreateViewHolder");
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        /*
        *  현재 제목+프로그램 사진
        * */
        int type = items.get(position).getType();
        Log.d(TAG, "onBindViewHolder:: position : "+position);
        Log.d(TAG, "onBindViewHolder::type : "+type);
        switch (type) {
            case ITEM_VIEW_TYPE_FUTURE :
                final FutureListingHolder holder_future = (FutureListingHolder) holder;
                holder_future.titleText_future.setText(getListingItem(position).getTitle());
                holder_future.timeText_future.setText(getListingItem(position).getStTime().substring(11,16));
                holder_future.reserveButton.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if (getListingItem(position).isReserved()) {
                            holder_future.reserveButton.setBackgroundResource(R.drawable.reserve_off);
                            getListingItem(position).setReserved(false);
                            Toast.makeText(context, "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        holder_future.reserveButton.setBackgroundResource(R.drawable.reserve_on);
                        getListingItem(position).setReserved(true);
                        Toast.makeText(context, "예약이 설정되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case ITEM_VIEW_TYPE_NOW :
                ListListingHolder holder_now = (ListListingHolder) holder;
                holder_now.titleText_now.setText(getListingItem(position).getTitle());
                break;

            case ITEM_VIEW_TYPE_PAST:
                PastListingHolder holder_past = (PastListingHolder)holder;
                holder_past.titleText_past.setText(getListingItem(position).getTitle());
                holder_past.timeText_past.setText(getListingItem(position).getStTime().substring(11,16));
                break;

        }

        /**
         * 과거 : 제목+시간
         */


        /**
         * 미래에는 제목+시간+예약버튼
         */

    }

    @Override
    public int getItemViewType(int position){
        return getListingItem(position).getType();
    }

    @Override
    public long getHeaderId(int position) {
        if(position ==0){
            return -1;
        } else{
            return Integer.parseInt(items.get(position).getStTime().substring(8,10));
        }
    }



    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(context).inflate(R.layout.sticky_header, parent,false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder holder, int position) {
        holder.date.setText(items.get(position).getStTime().substring(0,10));
    }


    @Override
    public int getItemCount() {
        //return items != null ? items.size() : 0;
        return items.size();
    }


    @Override
    public void setOnClickListner(OnItemClickListener clickListner) {
        this.onItemClickListener = clickListner;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
        Log.d("ListListingAdapter","notifyAdapter");
    }



    @Override
    public void addItems(ArrayList<DTO_SCHD> items) {
        Log.d("ListListingAdapter","addItems");
        this.items = items;
        for (int i = 0; i < items.size() ; i++) {
            int type = DateParser.compare(items.get(i).getStTime());
            items.get(i).setType(type);
        }
        mSectionIndices = getSectionIndices();
        mSectionDates = getSectionDates();
    }

    @Override
    public void clearItem() {
        if(items!=null){
            items.clear();
        }
    }

    @Override
    public DTO_SCHD getListingItem(int position) {
        return items.get(position);
    }

    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        sectionIndices.add(0);
        for (int i = 1; i < items.size(); i++) {
            if (DateParser.compare(items.get(i).getStTime())-DateParser.compare(items.get(i-1).getStTime())!=0) {
                //Log.d("getSectionIndice","add");
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) { // arraylist -> int[] 바꿔줌
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }

    // section이 바뀔 때 날짜(header) 바뀜
    public String[] getSectionDates() {
        String[] dates = new String[mSectionIndices.length];
        for (int i = 0; i<mSectionIndices.length; i++) {
            dates[i] = items.get(mSectionIndices[i]).getStTime();
            //Log.d("getSectionDates","add");
        }
        return dates;
    }



}
