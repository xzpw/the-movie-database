package com.example.whattowatch.ui.adaptor

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListner(var manager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    var currentItems = 0
    var totalItems = 0
    var scrolledItems = 0
    var page = 1
    var loading = false
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        currentItems = manager.childCount
        totalItems = manager.itemCount
        scrolledItems = manager.findFirstVisibleItemPosition()
        scrolledItems = manager.findFirstCompletelyVisibleItemPosition()
        if (!loading && currentItems + scrolledItems == totalItems) {
            loading = true
            page++
            loadMore(page)
        }
    }

    abstract fun loadMore(page: Int)
    fun setLoading(loading: Boolean) {
        this.loading = loading
    }

}