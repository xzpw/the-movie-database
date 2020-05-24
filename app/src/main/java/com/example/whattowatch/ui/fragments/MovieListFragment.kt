package com.example.whattowatch.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.whattowatch.R
import com.example.whattowatch.model.mymodel.MyMovieModel
import com.example.whattowatch.ui.adaptor.EndlessScrollListner
import com.example.whattowatch.ui.adaptor.ListAdaptor
import com.example.whattowatch.ui.presenter.MovieListPresenter
import com.example.whattowatch.ui.view.MovieListsView

class MovieListFragment : BaseFragment(), MovieListsView, OnRefreshListener {
    @InjectPresenter
    var mMovieListPresenter: MovieListPresenter? = null

    @ProvidePresenter
    fun provideMoviesPresenter(): MovieListPresenter {
        return MovieListPresenter(arguments!!.getInt(ARG_FRAGMENT_NUMBER)) // передача начальных параметров в презентор
    }

    private var mRecyclerView: RecyclerView? = null
    private val progressDialog: AlertDialog? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var endlessScrollListner: EndlessScrollListner? = null
    private var listAdaptor: ListAdaptor? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        mRecyclerView = view.findViewById(R.id.recycler)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        swipeRefreshLayout.setOnRefreshListener(this)
        //        progressDialog = new MaterialAlertDialogBuilder(getActivity())
//                .setCancelable(false)
//                .setView(R.layout.dialog_progress)
//                .show();
//        progressDialog.hide();
        initRecycler()
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        listAdaptor = ListAdaptor(null, router)
        mRecyclerView!!.layoutManager = layoutManager
        mRecyclerView!!.adapter = listAdaptor
        endlessScrollListner = object : EndlessScrollListner(layoutManager) {
            override fun loadMore(page: Int) { //переопределили абстрактрый метод, каждый раз инкриментируем страницу и загружаем
                mMovieListPresenter!!.loadMovieList(page)
                mRecyclerView!!.adapter!!.notifyDataSetChanged()
            }
        }
        mRecyclerView!!.addOnScrollListener(endlessScrollListner)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun showProgres(isVisible: Boolean) {
        showDialog(isVisible)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showMovies(movies: List<MyMovieModel>) {
        listAdaptor!!.setMovieList(movies)
        listAdaptor!!.notifyDataSetChanged()
    }

    override fun showError() {
        Log.d(LOG, "showError()")
    }

    override fun onRefreshed() {
        swipeRefreshLayout!!.isRefreshing = false
    }

    override fun onLoaded(data: List<MyMovieModel>) { // обновляем данные адаптера
        listAdaptor!!.setMovieList(data)
        listAdaptor!!.notifyDataSetChanged()
        endlessScrollListner!!.setLoading(false)
    }

    override fun onRefresh() {
        mMovieListPresenter!!.loadMovieList()
    }

    companion object {
        private const val ARG_FRAGMENT_NUMBER = "current_fragment_number"
        private const val LOG = "mylog"
        @JvmStatic
        fun newInstance(currentFragmentNumber: Int): MovieListFragment {
            val args = Bundle()
            args.putInt(ARG_FRAGMENT_NUMBER, currentFragmentNumber)
            val fragment = MovieListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}