package com.avijit.revivewastage.adapter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.R;
import com.avijit.revivewastage.fragment.admin.AdminCategoryFragment;
import com.avijit.revivewastage.model.Category;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.CategoryViewModel;

import java.util.List;

/**
 * Created by Avijit Acharjee on 9/4/2020 at 10:06 AM.
 * Email: avijitach@gmail.com.
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "CategoryRecyclerViewAda";

    private List<Category> categoryList;
    private Fragment fragment;
    private AppUtils appUtils;
    private CategoryViewModel viewModel;
    FragmentManager ft;
    public CategoryRecyclerViewAdapter(List<Category> categoryList, CategoryViewModel viewModel, Fragment fragment) {
        this.categoryList = categoryList;
        this.fragment = fragment;
        appUtils= new AppUtils(fragment.getContext());
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryNameTextView.setText(categoryList.get(position).getName());
        ft = fragment.getFragmentManager();
        holder.deleteImageView.setOnClickListener(v -> {
            new AlertDialog.Builder(fragment.getContext())
                    .setPositiveButton("Yes",(dialog, which) -> {
                        appUtils.dialog.show();
                        Category category = new Category();
                        category.setDelete(categoryList.get(position).getId());
                        appUtils.dialog.show();

                        viewModel.delete(category).observe(fragment, catResponse ->{
                            appUtils.dialog.dismiss();
                            Log.d(TAG, "onBindViewHolder: "+catResponse.toString());
                            if(catResponse.isNetworkSuccessful()){
                                ft.beginTransaction().replace(fragment.getId(),new AdminCategoryFragment()).commit();

                            }
                            else {
                                Toast.makeText(fragment.getContext(), "Failed to connect", Toast.LENGTH_SHORT).show();
                            }
                        });
                        /*viewModel.delete(userList.get(position).getId()).observe((UserCRUD) context,response->{
                            appUtils.dialog.dismiss();

                            if(response.isNetworkIsSuccessful()){
                                userList.remove(position);
                                Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                            else {
                                Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT).show();
                            }
                        });*/
                    })
                    .setNegativeButton("No",(dialog, which) -> {

                    })
                    .setMessage("Are you sure to delete?")
                    .create()
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
