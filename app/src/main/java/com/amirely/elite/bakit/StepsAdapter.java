package com.amirely.elite.bakit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amirely.elite.bakit.models.RecipeStep;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private List<RecipeStep> steps;
    private OnStepClickListener onStepClickListener;

    interface OnStepClickListener {
        void onStepClicked(RecipeStep recipeStep);
    }

    StepsAdapter(List<RecipeStep> steps, OnStepClickListener listener) {
        this.steps = steps;
        this.onStepClickListener = listener;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.steps_list_view, parent, false);

        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        RecipeStep recipeStep = steps.get(position);
        holder.stepDescriptionTv.setText(recipeStep.getShortDescription());
        holder.stepNumber.setText(recipeStep.getId());
        holder.recipeStep = recipeStep;
    }

    @Override
    public int getItemCount() {
        return steps == null ? 0 : steps.size();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView stepDescriptionTv;
        TextView stepNumber;
        RecipeStep recipeStep;

        StepsViewHolder(View itemView) {
            super(itemView);
            stepDescriptionTv = itemView.findViewById(R.id.recipe_step_title_tv);
            stepNumber = itemView.findViewById(R.id.step_number_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onStepClickListener.onStepClicked(recipeStep);
        }
    }
}
