package com.example.whattowatch.ui.adaptor

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.whattowatch.ui.fragments.MovieListFragment
import com.example.whattowatch.ui.fragments.MovieListFragment.Companion.newInstance
import java.util.*

class PagerAdaptor(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    private val PAGER_ITEM_COUNT = 3
    private val mListFragments: List<MovieListFragment> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return newInstance(i)
    }

    override fun getCount(): Int {
        return PAGER_ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Популярные"
            1 -> "TOP"
            2 -> "Ожидаемые"
            else -> throw RuntimeException("Incorrect item type")
        }
    }

    init {
        Log.d("mylog", "PagerFragment constructor")
    }
}