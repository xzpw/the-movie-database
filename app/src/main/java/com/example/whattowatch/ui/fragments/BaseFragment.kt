package com.example.whattowatch.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.whattowatch.R
import com.example.whattowatch.ui.Router
import com.google.android.material.dialog.MaterialAlertDialogBuilder

open class BaseFragment : MvpAppCompatFragment() {
    private lateinit var progressDialog: AlertDialog
    val router: Router
        get() = activity as Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
    }

    fun initDialog() {
        progressDialog = MaterialAlertDialogBuilder(activity)
                .setCancelable(false)
                .setView(R.layout.dialog_progress)
                .show()
        progressDialog.hide()
    }

    fun showDialog(isVisible: Boolean) {
        if (isVisible) {
            progressDialog!!.show()
        } else {
            progressDialog!!.hide()
        }
    }
}