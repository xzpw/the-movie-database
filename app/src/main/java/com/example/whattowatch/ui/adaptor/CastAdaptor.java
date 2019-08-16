package com.example.whattowatch.ui.adaptor;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whattowatch.R;
import com.example.whattowatch.api.IMovieAPI;
import com.example.whattowatch.api.MovieService;
import com.example.whattowatch.model.mymodel.MyCastModel;
import com.example.whattowatch.repository.RemoteMovieSource;
import com.example.whattowatch.ui.Router;

import java.util.ArrayList;
import java.util.List;

public class CastAdaptor extends RecyclerView.Adapter<CastAdaptor.CastHolder> {
    private List<MyCastModel> data = new ArrayList<>();
    private Router mRouter;

    public CastAdaptor(List<MyCastModel> data, Router router) {

        this.data = data;
        mRouter = router;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_item,parent,false);
        return new CastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder holder, int position) {
        Log.e("mylog","onBind");
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CastHolder extends RecyclerView.ViewHolder {

        private ImageView ivCastImage;
        private TextView tvName, tvCharacter;
        public CastHolder(@NonNull View itemView) {
            super(itemView);
            ivCastImage = itemView.findViewById(R.id.iv_cast);
            tvCharacter = itemView.findViewById(R.id.tv_character_cast);
            tvName = itemView.findViewById(R.id.tv_name_cast);

        }

        public void bind(int position){
            itemView.setOnClickListener(l ->{mRouter.showPerson(data.get(position).getId());});
            tvName.setText(data.get(position).getName());
            tvCharacter.setText(data.get(position).getCharacter());
            Glide.with(itemView.getContext())
                    .load(IMovieAPI.BASE_PICTURE+data.get(position).getProfilePath())
                    .fitCenter()
                    .into(ivCastImage);
        }
    }
}
