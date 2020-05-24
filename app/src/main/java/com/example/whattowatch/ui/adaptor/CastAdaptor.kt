package com.example.whattowatch.ui.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whattowatch.R
import com.example.whattowatch.api.IMovieAPI
import com.example.whattowatch.model.mymodel.MyCastModel
import com.example.whattowatch.ui.Router
import com.example.whattowatch.ui.adaptor.CastAdaptor.CastHolder
import java.util.*

class CastAdaptor(data: List<MyCastModel>, router: Router) : RecyclerView.Adapter<CastHolder>() {
    private var data: List<MyCastModel> = ArrayList()
    private val mRouter: Router
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cast_item, parent, false)
        return CastHolder(view)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CastHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivCastImage: ImageView
        private val tvName: TextView
        private val tvCharacter: TextView
        fun bind(position: Int) {
            itemView.setOnClickListener { l: View? -> mRouter.showPerson(data[position].id) }
            tvName.text = data[position].name
            tvCharacter.text = data[position].character
            Glide.with(itemView.context)
                    .load(IMovieAPI.BASE_PICTURE + data[position].profilePath)
                    .fitCenter()
                    .placeholder(R.drawable.ic_baseline_person_24px)
                    .into(ivCastImage)
        }

        init {
            ivCastImage = itemView.findViewById(R.id.iv_cast)
            tvCharacter = itemView.findViewById(R.id.tv_character_cast)
            tvName = itemView.findViewById(R.id.tv_name_cast)
        }
    }

    init {
        this.data = data
        mRouter = router
    }
}