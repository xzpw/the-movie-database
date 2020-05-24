package com.example.whattowatch.ui

interface Router {
    fun showPagerView()
    fun showDetailView(idMovie: Int) // будет принимать итем для детализации
    fun showFavorites()
    fun showSearch()
    fun showPerson(id: Int)
}