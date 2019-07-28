package com.example.whattowatch.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whattowatch.ui.adaptor.PagerAdaptor;
import com.example.whattowatch.R;

public class PagerHolderFragment extends Fragment {

   private TabLayout mTabLayout;
   private ViewPager mPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.pager_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewPager(view);


    }

    private void initViewPager(@NonNull View view) {
        mPager = view.findViewById(R.id.pager);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mPager.setOffscreenPageLimit(4);
        mPager.setAdapter(new PagerAdaptor(getActivity()
                .getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }


}
