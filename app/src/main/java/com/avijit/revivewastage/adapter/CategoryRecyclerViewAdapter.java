package com.avijit.revivewastage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.R;

import java.util.zip.Inflater;

/**
 * Created by Avijit Acharjee on 9/4/2020 at 10:06 AM.
 * Email: avijitach@gmail.com.
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImageView,editImageView,deleteImageView;
        TextView categoryNameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImageView = itemView.findViewById(R.id.cat_image);
            editImageView = itemView.findViewById(R.id.cat_edit);
            deleteImageView = itemView.findViewById(R.id.cat_delete);
            categoryNameTextView = itemView.findViewById(R.id.cat_name);
        }
    }
}
