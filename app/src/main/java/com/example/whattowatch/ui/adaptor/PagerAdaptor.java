package com.example.whattowatch.ui.adaptor;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.whattowatch.ui.fragments.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdaptor extends FragmentStatePagerAdapter {

    private final int PAGER_ITEM_COUNT = 3;
    private List<MovieListFragment> mListFragments = new ArrayList<>();

    public PagerAdaptor(FragmentManager fm) {
        super(fm);
        Log.d("mylog", "PagerFragment constructor");

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
        switch (position){
            case 0:
                return "Популярные";

            case 1:
                return "TOP";

            case 2:
                return "Ожидаемые";

                default:
                    throw new RuntimeException("Incorrect item type");
        }

    }
}
