package com.example.whattowatch.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.example.whattowatch.R;
import com.example.whattowatch.api.IMovieAPI;
import com.example.whattowatch.model.mymodel.MyPersonModel;
import com.example.whattowatch.ui.presenter.PersonPresenter;
import com.example.whattowatch.ui.view.PersonView;

public class PersonFragment extends BaseFragment implements PersonView {

    private static final String ARG_PERSON_ID = "id_person";

    private TextView tvName, tvDate, tvPlace, tvBiography;
    private ImageView ivAvatar;

    @InjectPresenter
    PersonPresenter presenter;

    @ProvidePresenter()
    PersonPresenter providePersonPresenter(){
        return new PersonPresenter(getArguments().getInt(ARG_PERSON_ID));
    }

    public static PersonFragment newInstance(int idPerson) {

        Bundle args = new Bundle();
        args.putInt(ARG_PERSON_ID,idPerson);
        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvDate = view.findViewById(R.id.tv_birthday);
        tvPlace = view.findViewById(R.id.tv_born_place);
        tvName = view.findViewById(R.id.tv_name_person);
        tvBiography = view.findViewById(R.id.tv_bio);
        ivAvatar = view.findViewById(R.id.iv_avatar);

    }

    @Override
    public void showProgress(boolean isVisible) {

    }

    @Override
    public void showPersonInfo(MyPersonModel personModel) {
        tvName.setText(personModel.getName());
        tvPlace.setText(personModel.getPlaceOfBirth());
        tvDate.setText(personModel.getBirthday());
        tvBiography.setText(personModel.getBiography());
        Glide.with(getContext())
                .load(IMovieAPI.BASE_PICTURE+personModel.getProfilePath())
                .fitCenter()
                .into(ivAvatar);

    }

    @Override
    public void showError() {

    }
}
