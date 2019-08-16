package com.example.whattowatch.ui;

public interface Router {


    void showPagerView();

    void showDetailView(int idMovie); // будет принимать итем для детализации

    void showFavorites();

    void showSearch();

    void showPerson(int id);
}
