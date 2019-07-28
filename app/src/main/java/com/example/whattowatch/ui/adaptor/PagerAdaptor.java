package com.example.whattowatch.ui.adaptor;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.whattowatch.ui.fragments.MovieListFragment;

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
        switch (position){
            case 0:
                return "TOP";

            case 1:
                return "Популярные";

            case 2:
                return "Скоро";

            case 3:
                return "Сейчас";

                default:
                    return "error";
        }

    }
}
