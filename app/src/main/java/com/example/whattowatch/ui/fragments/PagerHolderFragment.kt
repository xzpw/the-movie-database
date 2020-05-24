package com.example.whattowatch.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.whattowatch.R
import com.example.whattowatch.ui.adaptor.PagerAdaptor
import com.google.android.material.tabs.TabLayout

class PagerHolderFragment : BaseFragment(), View.OnClickListener {
    private var mTabLayout: TabLayout? = null
    private var mPager: ViewPager? = null
    private var tvSearch: TextView? = null
    private var ivSearchIcon: ImageView? = null
    private var ivFavoriteIcon: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView(view: View) {
        mPager = view.findViewById(R.id.pager)
        mTabLayout = view.findViewById(R.id.tab_layout)
        mPager.setOffscreenPageLimit(4)
        mPager.setAdapter(PagerAdaptor(activity
                .getSupportFragmentManager()))
        mTabLayout.setupWithViewPager(mPager)
        ivSearchIcon = view.findViewById(R.id.icon_iv_search)
        tvSearch = view.findViewById(R.id.icon_tv_search)
        ivFavoriteIcon = view.findViewById(R.id.icon_iv_favorite)
        ivFavoriteIcon.setOnClickListener(this)
        tvSearch.setOnClickListener(this)
        ivSearchIcon.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.icon_iv_search -> router.showSearch()
            R.id.icon_tv_search -> router.showSearch()
            R.id.icon_iv_favorite -> router.showFavorites()
        }
    }
}