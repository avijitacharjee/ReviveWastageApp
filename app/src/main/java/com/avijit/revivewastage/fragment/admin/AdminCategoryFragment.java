package com.avijit.revivewastage.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.avijit.revivewastage.R;
import com.avijit.revivewastage.adapter.CategoryRecyclerViewAdapter;
import com.avijit.revivewastage.model.Category;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 8:48 PM.
 * Email: avijitach@gmail.com.
 */
public class AdminCategoryFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Category> categoryList;
    private CategoryViewModel viewModel;
    private CategoryRecyclerViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_category,null,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        categoryList = new ArrayList<>();
        TextView textView = view.findViewById(R.id.text);
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        AppUtils appUtils = new AppUtils(getContext());
        appUtils.dialog.show();

        viewModel.all().observe(this,response->{
            appUtils.dialog.dismiss();
            textView.setText(response.toString());
            adapter = new CategoryRecyclerViewAdapter();
        });
    }
}
