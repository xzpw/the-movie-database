package com.example.whattowatch.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.whattowatch.R;
import com.example.whattowatch.ui.Router;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BaseFragment extends MvpAppCompatFragment {

    private AlertDialog progressDialog;
    Router getRouter(){
        return (Router) getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialog();
    }

    void initDialog(){
        progressDialog = new MaterialAlertDialogBuilder(getActivity())
                .setCancelable(false)
                .setView(R.layout.dialog_progress)
                .show();
        progressDialog.hide();
    }
    void showDialog(boolean isVisible){
        if (isVisible) {
            progressDialog.show();
        } else {
            progressDialog.hide();
        }
    }
}
