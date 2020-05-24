package com.example.whattowatch.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(): ViewModel() {
    val liveData = MutableLiveData<String>()

    init {
        liveData.postValue("livedata")

    }
}