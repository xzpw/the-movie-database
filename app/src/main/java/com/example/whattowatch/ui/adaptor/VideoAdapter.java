package com.example.whattowatch.ui.adaptor;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whattowatch.R;
import com.example.whattowatch.model.mymodel.MyVideoModel;
import com.example.whattowatch.repository.RemoteMovieSource;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private List <MyVideoModel> data;

    public VideoAdapter(List <MyVideoModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item,parent,false);

        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder{
        private ImageView ivVideo;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            ivVideo = itemView.findViewById(R.id.video_image);

        }

        void bind(int position){
            String thumbnailKey = data.get(position).getKey();
            Uri uri = Uri.parse(String
                    .format(RemoteMovieSource.VIDEO_YOUTUBE_THUMBNAIL,thumbnailKey));

            Glide.with(itemView.getContext())
                    .load(uri)
                    .fitCenter()
                    .placeholder(R.drawable.ic_baseline_ondemand_video_24px)
                    .into(ivVideo);

            itemView.setOnClickListener(l->{
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + thumbnailKey));
                itemView.getContext().startActivity(intent);
            });
        }

    }
}
