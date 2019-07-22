package com.example.whattowatch.navigation;

public interface Router {


    void shwoPagerView();

    void showDetailView(int idMovie); // будет принимать итем для детализации

    void showFavorits();
}
