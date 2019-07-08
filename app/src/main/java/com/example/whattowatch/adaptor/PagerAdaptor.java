package com.example.whattowatch.adaptor;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.whattowatch.fragments.MovieListFragment;

public class PagerAdaptor extends FragmentStatePagerAdapter {

    private final int PAGER_ITEM_COUNT = 4;

    public PagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return MovieListFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return PAGER_ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return MovieListFragment.Type.values()[position].name();
    }
}
