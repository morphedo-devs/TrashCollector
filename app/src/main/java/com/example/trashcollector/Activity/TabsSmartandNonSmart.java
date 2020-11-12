package com.example.trashcollector.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.trashcollector.Adapter.MyAdapter;
import com.example.trashcollector.R;
import com.google.android.material.tabs.TabLayout;

public class TabsSmartandNonSmart extends AppCompatActivity {
    TabLayout tab;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_smartand_non_smart);
        tab=findViewById(R.id.tabbar);
        viewPager=findViewById(R.id.viewpager);

        tab.addTab(tab.newTab().setText(R.string.smartphoneuser));
        tab.addTab(tab.newTab().setText(R.string.nonsmartuser));
        tab.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tab.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
