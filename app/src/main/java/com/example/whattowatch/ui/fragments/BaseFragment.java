package com.example.whattowatch.ui.fragments;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.whattowatch.ui.Router;

public class BaseFragment extends MvpAppCompatFragment {

    Router getRouter(){
        return (Router) getActivity();
    }


}
