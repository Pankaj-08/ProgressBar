package com.example.progressbarscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(new PageAdapterOrdering(this));

        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout,
                viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position)
                        {
                            case 0:
                            {
                                tab.setText("All");
                                tab.setIcon(R.drawable.all);
                                break;
                            }
                            case 1:
                            {
                                tab.setText("Code");
                                tab.setIcon(R.drawable.code);
                                break;
                            }
                            case 2:
                            {
                                tab.setText("Design");
                                tab.setIcon(R.drawable.design);
                                break;
                            }
                            case 3:
                            {
                                tab.setText("DB");
                                tab.setIcon(R.drawable.db);
                                BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                                badgeDrawable.setBackgroundColor(
                                        ContextCompat.getColor(getApplicationContext(),R.color.colorAccent)
                                );
                                badgeDrawable.setVisible(true);
                                break;
                            }

                        }
                    }
                });
        tabLayoutMediator.attach();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }



}