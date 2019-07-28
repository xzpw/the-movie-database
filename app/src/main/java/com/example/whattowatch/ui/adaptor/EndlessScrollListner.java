package com.example.whattowatch.ui.adaptor;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessScrollListner extends RecyclerView.OnScrollListener {


    LinearLayoutManager manager;
    int currentItems;
    int totalItems;
    int scrolledItems;
    int page = 1;
    boolean loading = false;



    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        currentItems = manager.getChildCount();
        totalItems = manager.getItemCount();
        scrolledItems = manager.findFirstVisibleItemPosition();
        scrolledItems = manager.findFirstCompletelyVisibleItemPosition();
        if( !loading && currentItems + scrolledItems == totalItems){

            loading = true;
            page++;
            loadMore(page);
        }

    }

    public abstract void loadMore(int page);

    public void setLoading(boolean loading){
        this.loading = loading;
    }

    public EndlessScrollListner(LinearLayoutManager manager) {
        this.manager = manager;
    }
}
