package com.avijit.revivewastage.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.ProductDetailsActivity;
import com.avijit.revivewastage.R;
import com.avijit.revivewastage.model.Product;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllProductRecyclerViewAdapter extends RecyclerView.Adapter<AllProductRecyclerViewAdapter.ViewHolder>{
    private List<Product> productList;
    private Context context;
    private boolean isSeller;
    public AllProductRecyclerViewAdapter(List<Product> productList, Context context,boolean isSeller) {
        this.productList = productList;
        this.context = context;
        this.isSeller = isSeller;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productNameTextView.setText(productList.get(position).getName());
        holder.priceTextView.setText(productList.get(position).getPrice());
        Picasso.get().load("http://finalproject.xyz/revive_wastage/images/"+productList.get(position).getImage()).into(holder.productImageView);
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("product",new Gson().toJson(productList.get(position)));
            intent.putExtra("seller",isSeller);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, holder.productImageView, "profile");
            context.startActivity(intent, options.toBundle());
            //context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView,priceTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_image_view);
            productNameTextView = itemView.findViewById(R.id.product_name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
        }
    }
}
