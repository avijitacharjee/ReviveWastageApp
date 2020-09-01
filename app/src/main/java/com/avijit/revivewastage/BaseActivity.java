package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;

import com.avijit.revivewastage.utils.AppUtils;
import com.google.android.material.navigation.NavigationView;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 5:08 PM.
 * Email: avijitach@gmail.com.
 */
class BaseActivity extends AppCompatActivity {
    AppUtils appUtils;
    DrawerLayout drawer;
    Toolbar toolbar;
    protected void setToolbar(){
        toolbar= findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black_1));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white_1));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white_1));
    }
}
