package com.amirely.elite.bakit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeIngredient;
import com.amirely.elite.bakit.models.RecipeStep;
import com.amirely.elite.bakit.utils.Navigator;

import java.util.List;


public class RecipeStepsFragment extends Fragment implements StepsAdapter.OnStepClickListener {
    //    private OnFragmentInteractionListener mListener;

    List<RecipeStep> stepList;
    List<RecipeIngredient> ingredients;
    Recipe currentRecipe;

    RecyclerView recipeRecyclerView;

    Navigator navigator;

    FragmentManager manager;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    public static RecipeStepsFragment newInstance(Recipe recipe) {

        RecipeStepsFragment fragment = new RecipeStepsFragment();
        fragment.stepList = recipe.getSteps();
        fragment.ingredients = recipe.getIngredients();
        fragment.currentRecipe = recipe;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipe_steps_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);

        StepsAdapter stepsAdapter = new StepsAdapter(currentRecipe, this);

        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setAdapter(stepsAdapter);


        TextView ingredientsTv = view.findViewById(R.id.step_ingredients_tv);






        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStepClicked(RecipeStep recipeStep) {

        manager = getFragmentManager();

        navigator = new Navigator(manager);

        navigator.navigateTo(RecipeStepDetailsFragment.newInstance(recipeStep));
    }
}