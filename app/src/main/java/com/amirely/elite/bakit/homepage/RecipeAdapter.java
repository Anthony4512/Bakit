package com.amirely.elite.bakit.homepage;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amirely.elite.bakit.R;
import com.amirely.elite.bakit.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;

    private OnRecipeClickListener recipeClickListener;

    interface OnRecipeClickListener {
        void onRecipeClicked(Recipe recipe);
    }

    RecipeAdapter(List<Recipe> recipeList, OnRecipeClickListener clickListener) {
        this.recipeList = recipeList;
        this.recipeClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_list_view, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.currentRecipe.setText(recipe.getName());
    }


    @Override
    public int getItemCount() {
        return recipeList == null ? 0 : recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView currentRecipe;
        ConstraintLayout itemLayout;
        Recipe recipe;

        RecipeViewHolder(View itemView) {
            super(itemView);
            currentRecipe = itemView.findViewById(R.id.recipe_title_tv);
            itemLayout = itemView.findViewById(R.id.recipe_item_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("HOLDER", "CLICK LISTENER HAS BEEN PRESSED");
            recipeClickListener.onRecipeClicked(recipeList.get(getAdapterPosition()));
        }
    }



}
