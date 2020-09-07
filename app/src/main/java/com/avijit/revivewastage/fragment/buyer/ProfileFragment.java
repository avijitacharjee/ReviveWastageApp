package com.avijit.revivewastage.fragment.buyer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avijit.revivewastage.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Avijit Acharjee on 9/7/2020 at 2:53 PM.
 * Email: avijitach@gmail.com.
 */
public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    TextView nameTextView,emailTextView,phoneTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,null,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTextView = view.findViewById(R.id.name_text_view);
        emailTextView = view.findViewById(R.id.email_text_view);
        phoneTextView = view.findViewById(R.id.phone_text_view);
        try {
            JSONObject jsonObject = new JSONObject(getActivity().getSharedPreferences("Revive", Context.MODE_PRIVATE).getString("user",""));
            JSONObject data = jsonObject.getJSONObject("data");
            nameTextView.setText(data.getString("name"));
            emailTextView.setText(data.getString("email"));
            phoneTextView.setText(data.getString("phone"));
        } catch (JSONException e) {
            Log.d(TAG, "onCreate: "+e.toString());
        }
    }
}
