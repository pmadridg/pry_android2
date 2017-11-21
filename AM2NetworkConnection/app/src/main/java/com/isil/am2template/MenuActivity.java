package com.isil.am2template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Pablo Claus on 11/5/2017.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    private View cardvSwipeTabs;
    private View cardvNavDrawer;
    private View cardvVpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardvSwipeTabs= findViewById(R.id.cardvSwipeTabs);
        cardvNavDrawer= findViewById(R.id.cardvNavDrawer);
        cardvVpager= findViewById(R.id.cardvVpager);

        cardvSwipeTabs.setOnClickListener(this);
        cardvNavDrawer.setOnClickListener(this);
        cardvVpager.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardvSwipeTabs:
                gotoSwipeTabs();
                break;
            case R.id.cardvNavDrawer:
                gotoNavDrawer();
                break;
            case R.id.cardvVpager:
                gotoVPagerScreenSlides();
                break;
        }
    }

    private void gotoSwipeTabs() {
        startActivity(new Intent(this,PlaceActivity.class));
    }

    private void gotoNavDrawer() {
        startActivity(new Intent(this,BuffetActivity.class));
    }

    private void gotoVPagerScreenSlides() {
        startActivity(new Intent(this,CartActivity.class));
    }
}
