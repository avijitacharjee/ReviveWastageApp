package com.avijit.revivewastage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.ApproveActivity;
import com.avijit.revivewastage.ProductDetailsActivity;
import com.avijit.revivewastage.R;
import com.avijit.revivewastage.model.Bid;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.ProductViewModel;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Avijit Acharjee on 9/28/2020 at 11:40 AM.
 * Email: avijitach@gmail.com.
 */
public class BidsRecyclerViewAdapter extends RecyclerView.Adapter<BidsRecyclerViewAdapter.ViewHolder> {
    private List<Bid> bidList;
    private boolean isSeller;
    private Context context;
    AppUtils appUtils;
    ProductViewModel viewModel;
    public BidsRecyclerViewAdapter(Context context,List<Bid> bidList, boolean isSeller){
        this.context = context;
        this.bidList = bidList;
        this.isSeller = isSeller;
        viewModel = ViewModelProviders.of((ProductDetailsActivity) context).get(ProductViewModel.class);
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
        if(!isSeller){
            holder.approveTextView.setVisibility(View.GONE);
        }
        else {
            holder.approveTextView.setOnClickListener(v->{
                appUtils.dialog.show();
                viewModel.approveBid(bidList.get(position)).observe((ProductDetailsActivity)context,response->{
                   if(!response.isNetworkSuccessful()){
                       Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT).show();
                       return;
                   }
                   Intent intent = new Intent(context, ApproveActivity.class);
                   intent.putExtra("bid",new Gson().toJson(bidList.get(position)));
                   context.startActivity(intent);
                });
            });
        }
    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bidAmountTextView,bidDetailsTextView,approveTextView;
        public ViewHolder(View itemView){
            super(itemView);
            bidAmountTextView = itemView.findViewById(R.id.bid_amount_text_view);
            bidDetailsTextView = itemView.findViewById(R.id.bid_details_text_view);
            approveTextView = itemView.findViewById(R.id.approveButton);
        }
    }
}
