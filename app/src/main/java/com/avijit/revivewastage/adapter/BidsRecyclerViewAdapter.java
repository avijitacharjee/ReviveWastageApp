package com.avijit.revivewastage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.R;
import com.avijit.revivewastage.model.Bid;

import java.util.List;

/**
 * Created by Avijit Acharjee on 9/28/2020 at 11:40 AM.
 * Email: avijitach@gmail.com.
 */
public class BidsRecyclerViewAdapter extends RecyclerView.Adapter<BidsRecyclerViewAdapter.ViewHolder> {
    private List<Bid> bidList;
    public BidsRecyclerViewAdapter(List<Bid> bidList){
        this.bidList = bidList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bids,null,false));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bidDetailsTextView.setText(bidList.get(position).getDescription());
        holder.bidAmountTextView.setText(bidList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bidAmountTextView,bidDetailsTextView;
        public ViewHolder(View itemView){
            super(itemView);
            bidAmountTextView = itemView.findViewById(R.id.bid_amount_text_view);
            bidDetailsTextView = itemView.findViewById(R.id.bid_details_text_view);
        }
    }
}
