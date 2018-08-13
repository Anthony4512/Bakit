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

    StepsAdapter(List<RecipeStep> steps) {
        this.steps = steps;
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
    }

    @Override
    public int getItemCount() {
        return steps == null ? 0 : steps.size();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView stepDescriptionTv;
        TextView stepNumber;

        StepsViewHolder(View itemView) {
            super(itemView);
            stepDescriptionTv = itemView.findViewById(R.id.recipe_step_title_tv);
            stepNumber = itemView.findViewById(R.id.step_number_tv);
        }
    }
}
