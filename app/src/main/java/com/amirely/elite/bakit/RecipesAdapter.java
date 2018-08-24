package com.amirely.elite.bakit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {


    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class RecipesViewHolder extends RecyclerView.ViewHolder {


        public RecipesViewHolder(View itemView) {
            super(itemView);
        }


    }
}
