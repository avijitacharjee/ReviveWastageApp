package com.avijit.revivewastage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.avijit.revivewastage.fragment.admin.AdminCategoryFragment;
import com.avijit.revivewastage.fragment.admin.AdminHomeFragment;
import com.avijit.revivewastage.utils.AppUtils;
import com.avijit.revivewastage.utils.EndDrawerToggle;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends BaseActivity {
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        appUtils = new AppUtils(this);
        setToolbar();
        AdminHomeFragment homeFragment = new AdminHomeFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.admin_fragment_container,homeFragment);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item->{
            int id = item.getItemId();
            switch (id){
                case R.id.nav_home : {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.admin_fragment_container,new AdminHomeFragment());
                    ft.commit();
                    closeDrawer();
                    break;
                }
                case R.id.nav_category: {
                    ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.admin_fragment_container,new AdminCategoryFragment());
                    ft.commit();
                    closeDrawer();
                    break;
                }


            }

            return true;
        });
        EndDrawerToggle toggle = new EndDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(v->super.onBackPressed());
        /*Menu menu = navigationView.getMenu();
        MenuItem menuItem= menu.findItem(R.id.group_title_1);
        SpannableString s = new SpannableString(menuItem.getTitle());
        s.setSpan(new TextAppearanceSpan(this,R.style.TextAppearance44),0,s.length(),0);
        menuItem.setTitle(s);*/
    }
    private void closeDrawer(){
        if(drawer.isDrawerOpen(Gravity.RIGHT)){
            drawer.closeDrawer(Gravity.RIGHT);
        }
    }

}