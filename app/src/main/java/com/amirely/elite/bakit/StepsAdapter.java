package com.amirely.elite.bakit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeIngredient;
import com.amirely.elite.bakit.models.RecipeStep;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private List<RecipeStep> steps;
    private OnStepClickListener onStepClickListener;
    private List<RecipeIngredient> ingredients;

    StepsAdapter(Recipe recipe, OnStepClickListener listener) {
        this.steps = recipe.getSteps();
        this.ingredients = recipe.getIngredients();
        this.onStepClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 1;
        }
        else {
            return 2;
        }
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if(viewType == 1) {
            view = inflater.inflate(R.layout.ingredients_list_view, parent, false);
        }
        else {
            view = inflater.inflate(R.layout.steps_list_view, parent, false);
        }

        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        if(position == 0) {
            String ingredientText = "";
            for (RecipeIngredient ingredient : ingredients) {
                ingredientText += ingredient.getQuantity() + " " + ingredient.getMeasure() + " " + ingredient.getIngredient() + "\n";
            }
            holder.ingredientsTv.setText(ingredientText);
        }
        else {
            RecipeStep recipeStep = steps.get(position);
            holder.stepDescriptionTv.setText(recipeStep.getShortDescription());
            holder.stepNumber.setText(recipeStep.getId());
            holder.recipeStep = recipeStep;
        }
    }

    @Override
    public int getItemCount() {
        return steps == null ? 0 : steps.size();
    }

    interface OnStepClickListener {
        void onStepClicked(int position);
    }

    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stepDescriptionTv;
        TextView stepNumber;
        RecipeStep recipeStep;

        TextView ingredientsTv;

        StepsViewHolder(View itemView) {
            super(itemView);

            int itemViewId = itemView.getId();
            int idForIngredientsView = R.id.ingredients_layout;

            if(itemViewId == idForIngredientsView) {
                ingredientsTv = itemView.findViewById(R.id.step_ingredients_tv);
            }
            else {
                stepDescriptionTv = itemView.findViewById(R.id.recipe_step_title_tv);
                stepNumber = itemView.findViewById(R.id.step_number_tv);
                itemView.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View view) {
            onStepClickListener.onStepClicked(getAdapterPosition());
        }
    }
}
