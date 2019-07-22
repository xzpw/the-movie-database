package com.example.whattowatch.fragments;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.whattowatch.navigation.Router;

public class BaseFragment extends MvpAppCompatFragment {

    Router getRouter(){
        return (Router) getActivity();
    }


}
