package com.example.progressbarscreen;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapterOrdering extends FragmentStateAdapter {


    public PageAdapterOrdering(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case (0):
                return new AllFragment();
            case (1):
                return new CodeFragment();
            case (2):
                return new DesignFragment();
            default:
                return new DBFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
