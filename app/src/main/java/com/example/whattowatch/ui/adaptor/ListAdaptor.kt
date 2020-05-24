package com.example.whattowatch.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whattowatch.App.Companion.instance
import com.example.whattowatch.R
import com.example.whattowatch.api.IMovieAPI
import com.example.whattowatch.model.mymodel.MyMovieModel
import com.example.whattowatch.ui.Router
import com.example.whattowatch.ui.adaptor.ListAdaptor.MovieHolder
import java.util.*

class ListAdaptor(movies: List<MyMovieModel>?, private val navigationRouter: Router) : RecyclerView.Adapter<MovieHolder>() {
    private var mMovieList: List<MyMovieModel>? = ArrayList()
    fun setMovieList(data: List<MyMovieModel>?) {
        mMovieList = data
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movie_list_item, viewGroup, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(movieHolder: MovieHolder, i: Int) {
        movieHolder.bind(i)
    }

    override fun getItemCount(): Int {
        return if (mMovieList == null) {
            0
        } else {
            mMovieList!!.size
        }
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val tvTitle: TextView
        private val tvDate: TextView
        private val tvRate: TextView
        private val ratingBar: RatingBar
        fun bind(i: Int) {
            tvTitle.text = mMovieList!![i].title
            tvDate.text = mMovieList!![i].date
            ratingBar.rating = mMovieList!![i].rate
            tvRate.text = mMovieList!![i].rate.toString() + "/10"
            Glide.with(itemView.context)
                    .load(IMovieAPI.BASE_PICTURE + mMovieList!![i].imageLink)
                    .fitCenter()
                    .into(imageView)
            itemView.setOnClickListener { l: View? -> navigationRouter.showDetailView(mMovieList!![i].movieId) }
        }

        init {
            imageView = itemView.findViewById(R.id.rv_imageView)
            tvDate = itemView.findViewById(R.id.rv_date)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            tvRate = itemView.findViewById(R.id.rv_rate)
            tvTitle = itemView.findViewById(R.id.rv_title)
        }
    }

    init {
        mMovieList = movies
        instance.getAppComponent().inject(this)
    }
}