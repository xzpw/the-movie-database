package com.example.whattowatch.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whattowatch.di.viewmodel.ViewModelFactory
import com.example.whattowatch.di.viewmodel.ViewModelKey
import com.example.whattowatch.ui.favorite.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel


    @Binds
     abstract fun bindViewModelFactory(factory: ViewModelFactory) :ViewModelProvider.Factory
}