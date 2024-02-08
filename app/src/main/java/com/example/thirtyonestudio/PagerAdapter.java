package com.example.thirtyonestudio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FotoStudioFragment();
            case 1:
                return new FotoOutdoorFragment();
            case 2:
                return new LainnyaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        String title = null;
        if (position == 0){
            title = "Foto Studio";
        } else if (position == 1) {
            title = "Foto Outdoor";
        }else {
            title = "Lainnya";
        }
        return title;
    }

}
