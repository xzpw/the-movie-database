package com.example.whattowatch.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.whattowatch.ui.adaptor.PagerAdaptor;
import com.example.whattowatch.R;

public class PagerHolderFragment extends BaseFragment implements View.OnClickListener {

   private TabLayout mTabLayout;
   private ViewPager mPager;
   private TextView tvSearch;
   private ImageView ivSearchIcon;
   private ImageView ivFavoriteIcon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView(@NonNull View view) {
        mPager = view.findViewById(R.id.pager);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mPager.setOffscreenPageLimit(4);
        mPager.setAdapter(new PagerAdaptor(getActivity()
                .getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
        ivSearchIcon = view.findViewById(R.id.icon_iv_search);
        tvSearch = view.findViewById(R.id.icon_tv_search);
        ivFavoriteIcon = view.findViewById(R.id.icon_iv_favorite);
        ivFavoriteIcon.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        ivSearchIcon.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.icon_iv_search:
                getRouter().showSearch();
                break;
            case R.id.icon_tv_search:
                getRouter().showSearch();
                break;
            case R.id.icon_iv_favorite:
                getRouter().showFavorites();
                break;
        }

    }
}
