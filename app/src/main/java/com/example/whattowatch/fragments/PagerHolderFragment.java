package com.example.whattowatch.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whattowatch.adaptor.PagerAdaptor;
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
        mPager.setAdapter(new PagerAdaptor(getActivity()
                .getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
    }


}
