package com.example.whattowatch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.whattowatch.R
import com.example.whattowatch.api.IMovieAPI
import com.example.whattowatch.model.mymodel.MyCastModel
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyVideoModel
import com.example.whattowatch.ui.adaptor.CastAdaptor
import com.example.whattowatch.ui.adaptor.VideoAdapter
import com.example.whattowatch.ui.presenter.DetailPresenter
import com.example.whattowatch.ui.view.DetailMovieView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class DetailFragment : BaseFragment(), DetailMovieView {
    private var tvTitle: TextView? = null
    private var tvDate: TextView? = null
    private var tvOverView: TextView? = null
    private var ivPoster: ImageView? = null
    private var ivBackDrop: ImageView? = null
    private var rateBar: RatingBar? = null
    private lateinit var rvTrailerVideo: RecyclerView
    private lateinit var rvCast: RecyclerView
    private lateinit var fabFavotite: FloatingActionButton
    private lateinit var cvInfoError: CardView
    // private SwipeRefreshLayout swipeRefreshLayout;
    @InjectPresenter
    var presenter: DetailPresenter? = null

    @ProvidePresenter
    fun provideDetailPresenter(): DetailPresenter {
        return DetailPresenter(requireArguments().getInt(ARG_MOVIE_ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.detail_fragment_scrolling
                , container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        //        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });
    }

    private fun initView(view: View) {
        ivBackDrop = view.findViewById(R.id.backdrop)
        ivPoster = view.findViewById(R.id.poster)
        tvDate = view.findViewById(R.id.detail_date)
        //tvRate = view.findViewById(R.id.detail_rate);
        tvTitle = view.findViewById(R.id.detail_title)
        rateBar = view.findViewById(R.id.detail_rate)
        tvOverView = view.findViewById(R.id.detail_overview)
        //swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        cvInfoError = view.findViewById(R.id.detail_info_error)
        rvTrailerVideo = view.findViewById(R.id.rv_video_trailer)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvTrailerVideo.setLayoutManager(layoutManager)
        rvTrailerVideo.setItemAnimator(DefaultItemAnimator())
        fabFavotite = view.findViewById(R.id.fab)
        fabFavotite.setOnClickListener(View.OnClickListener { l: View? -> presenter!!.add2Favorites() })
        rvCast = view.findViewById(R.id.rv_cast)
        val layoutManagerCast = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvCast.setLayoutManager(layoutManagerCast)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvCast)
    }

    override fun showProgress(isVisible: Boolean) {
        showDialog(isVisible)
    }

    override fun showMovieInfo(movie: MyDetailModel) {
        cvInfoError!!.visibility = View.INVISIBLE
        Glide.with(requireContext())
                .load(IMovieAPI.BASE_PICTURE + movie.backdroLink)
                .fitCenter()
                .into(ivBackDrop!!)
        Glide.with(requireContext())
                .load(IMovieAPI.BASE_PICTURE + movie.posterLink)
                .fitCenter()
                .into(ivPoster!!)
        tvDate!!.text = movie.releaseDate
        tvTitle!!.text = movie.name
        rateBar!!.rating = movie.rate ?: 0f
        tvOverView!!.text = movie.overview
    }

    override fun showMovieTrailer(videoModel: List<MyVideoModel>) {
        rvTrailerVideo.adapter = VideoAdapter(videoModel)
    }

    override fun showMovieCast(castModel: List<MyCastModel>) {
        rvCast.adapter = CastAdaptor(castModel, router)
    }

    override fun showErrorCast() {}
    override fun showErrorInfo() {
        cvInfoError.visibility = View.VISIBLE
    }

    override fun showErrorTrailer() {}
    override fun onSave(isSaved: Boolean) {
        if (isSaved) {
            view?.let { Snackbar.make(it, "Добавлено в избранное", 2000).show() }
        } else {
            view?.let { Snackbar.make(it, "Удалено с избранного", 2000).show() }
        }
    }

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"
        @JvmStatic
        fun newInstance(movieId: Int): DetailFragment {
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, movieId)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}