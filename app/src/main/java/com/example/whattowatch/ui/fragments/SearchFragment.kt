package com.example.whattowatch.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.whattowatch.R
import com.example.whattowatch.model.mymodel.MyMovieModel
import com.example.whattowatch.ui.adaptor.EndlessScrollListner
import com.example.whattowatch.ui.adaptor.ListAdaptor
import com.example.whattowatch.ui.presenter.SearchPresenter
import com.example.whattowatch.ui.view.MovieListsView

class SearchFragment : BaseFragment(), MovieListsView {
    @InjectPresenter
    var mPresenter: SearchPresenter? = null
    private var mSearchView: SearchView? = null
    private var mRecyclerView: RecyclerView? = null
    private var endlessScrollListner: EndlessScrollListner? = null
    private var listAdaptor: ListAdaptor? = null
    private var searchQuery: String? = null
    private var ivCloud: ImageView? = null
    private var progressBar: ProgressBar? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        Log.d("mylog", "SearchFragment() onCreateView")
        initView(view)
        return view
    }

    private fun initView(view: View) {
        mRecyclerView = view.findViewById(R.id.search_rv)
        mSearchView = view.findViewById(R.id.searchView)
        val layoutManager = LinearLayoutManager(context)
        listAdaptor = ListAdaptor(null, router)
        mRecyclerView.setLayoutManager(layoutManager)
        mRecyclerView.setAdapter(listAdaptor)
        ivCloud = view.findViewById(R.id.iv_cloud_off_search)
        progressBar = view.findViewById(R.id.progressBar_search)
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 1) {
                    progressBar.setVisibility(View.VISIBLE)
                    mPresenter!!.onSearch(newText, 1, false)
                    searchQuery = newText
                    return true
                }
                return false
            }
        })
        endlessScrollListner = object : EndlessScrollListner(layoutManager) {
            override fun loadMore(page: Int) {
                mPresenter!!.onSearch(searchQuery, page, true)
            }
        }
        mRecyclerView.addOnScrollListener(endlessScrollListner)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mylog", "SearchFragment() onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mylog", "SearchFragment() onDestroy")
    }

    override fun showProgres(isVisible: Boolean) {
        if (isVisible) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.INVISIBLE
        }
    }

    override fun showMovies(movies: List<MyMovieModel>) {
        ivCloud!!.visibility = View.INVISIBLE
        listAdaptor!!.setMovieList(movies)
        listAdaptor!!.notifyDataSetChanged()
    }

    override fun showError() {
        ivCloud!!.visibility = View.VISIBLE
    }

    override fun onRefreshed() {}
    override fun onLoaded(data: List<MyMovieModel>) {
        listAdaptor!!.setMovieList(data)
        listAdaptor!!.notifyDataSetChanged()
        endlessScrollListner!!.setLoading(false)
    }
}