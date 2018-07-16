package com.example.android.newssandwich;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by M on 14/07/2018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;
    public FragmentAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
            return new  RecentFragment();
        else if(position == 1)
            return new PoliticsFragment();
        else if(position == 2)
            return new ArtFragment();
        else
            return new SportFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return context.getString(R.string.title_Recent_fragment);
        else if(position == 1)
            return context.getString(R.string.title_Politics_fragment);
        else if(position == 2)
            return context.getString(R.string.title_Art_fragment);
        else
            return context.getString(R.string.title_sport_fragment);
    }
}
