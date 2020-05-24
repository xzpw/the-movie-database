package com.example.whattowatch.ui.adaptor

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whattowatch.R
import com.example.whattowatch.model.mymodel.MyVideoModel
import com.example.whattowatch.repository.RemoteMovieSource
import com.example.whattowatch.ui.adaptor.VideoAdapter.VideoHolder

class VideoAdapter(private val data: List<MyVideoModel>) : RecyclerView.Adapter<VideoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.video_item, parent, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivVideo: ImageView
        fun bind(position: Int) {
            val thumbnailKey = data[position].key
            val uri = Uri.parse(String.format(RemoteMovieSource.VIDEO_YOUTUBE_THUMBNAIL, thumbnailKey))
            Glide.with(itemView.context)
                    .load(uri)
                    .fitCenter()
                    .placeholder(R.drawable.ic_baseline_ondemand_video_24px)
                    .into(ivVideo)
            itemView.setOnClickListener { l: View? ->
                val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=$thumbnailKey"))
                itemView.context.startActivity(intent)
            }
        }

        init {
            ivVideo = itemView.findViewById(R.id.video_image)
        }
    }

}