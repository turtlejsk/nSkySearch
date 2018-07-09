package com.skysearch.itm.nskysearch.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.skysearch.itm.nskysearch.R;
import com.skysearch.itm.nskysearch.data.ListingItem;
import com.skysearch.itm.nskysearch.data.dto.DTO_SCHD;
import com.skysearch.itm.nskysearch.listener.OnItemClickListener;
import com.skysearch.itm.nskysearch.view.adapters.contracts.ListingAdapterContract;
import com.skysearch.itm.nskysearch.view.adapters.holders.ListListingHolder;
import com.skysearch.itm.nskysearch.view.adapters.holders.ListingHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class ListListingAdapter extends RecyclerView.Adapter<ListingHolder> implements ListingAdapterContract.Model, ListingAdapterContract.View {



    public ArrayList<DTO_SCHD> items = new ArrayList<DTO_SCHD>();
    public Context context;
    public OnItemClickListener onItemClickListener;

    public ListListingAdapter(Context context){this.context = context;}

    @NonNull
    @Override
    public ListingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if() 문으로 시간대별로 다른 뷰 생성
        final View view = LayoutInflater.from(context).inflate(R.layout.list_item_now, parent,false);

        Log.d("ListListingAdapter","onCreateViewHolder");
        return new ListListingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListingHolder holder, int position) {


        /*
        *  현재 제목+프로그램 사진
        * */
        holder.titleText.setText(getItem(position).getTitle());

        /**
         * 과거 : 제목+시간
         */


        /**
         * 미래에는 제목+시간+예약버튼
         */


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
    }

    @Override
    public void clearItem() {
        if(items!=null){
            items.clear();
        }
    }

    @Override
    public DTO_SCHD getItem(int position) {
        return items.get(position);
    }
}
