package com.avijit.revivewastage.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.revivewastage.R;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 8:21 PM.
 * Email: avijitach@gmail.com.
 */
public class AdminHomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_home,null,false);
    }
}
