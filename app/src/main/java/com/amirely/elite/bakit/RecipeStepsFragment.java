package com.amirely.elite.bakit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amirely.elite.bakit.homepage.RecipeAdapter;
import com.amirely.elite.bakit.homepage.RecipesFragment;
import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeIngredient;
import com.amirely.elite.bakit.models.RecipeStep;

import java.util.ArrayList;
import java.util.List;



public class RecipeStepsFragment extends Fragment {
    //    private OnFragmentInteractionListener mListener;

    List<RecipeStep> stepList;

    RecyclerView recipeRecyclerView;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    public static RecipeStepsFragment newInstance(List<RecipeStep> recipeSteps) {

        RecipeStepsFragment fragment = new RecipeStepsFragment();
        fragment.stepList = recipeSteps;
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

        StepsAdapter stepsAdapter = new StepsAdapter(stepList);

        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setAdapter(stepsAdapter);

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

}