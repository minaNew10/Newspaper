package com.example.android.newssandwich;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //api key = b70d4f5d-e00b-44c3-8d15-6c700f643073
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager_main);
        viewPager.setAdapter(new FragmentAdapter(MainActivity.this,getSupportFragmentManager()));
        tabLayout = findViewById(R.id.tablayout_main);
        tabLayout.setupWithViewPager(viewPager);

    }
}
