package com.amirely.elite.bakit;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RecipeStepsFragment extends Fragment implements StepsAdapter.OnStepClickListener {
    //    private OnFragmentInteractionListener mListener;

    ArrayList<RecipeStep> stepList;
    List<RecipeIngredient> ingredients;
    Recipe currentRecipe;

    RecyclerView recipeRecyclerView;

    Navigator navigator;

    int stepPosition;

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
        stepPosition = 1;

        manager = getFragmentManager();

        navigator = new Navigator(manager);

        if(savedInstanceState != null) {
            stepList = savedInstanceState.getParcelableArrayList("stepList");
            currentRecipe = savedInstanceState.getParcelable("recipe");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            navigator.makeStepsFullScreen(this, RecipeStepDetailsFragment.newInstance(currentRecipe.getSteps(), stepPosition));
        }
//        else {
//            LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
//            recipeRecyclerView.setLayoutManager(layoutManager);
//        }


        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipe_steps_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);

        StepsAdapter stepsAdapter = new StepsAdapter(currentRecipe, this);

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

    @Override
    public void onStepClicked(int position) {

        this.stepPosition = position;

        navigator.navigateTo(RecipeStepDetailsFragment.newInstance(stepList, position));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("stepsList", stepList);
        outState.putParcelable("recipe", currentRecipe);
    }


    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle(currentRecipe.getName());

    }
}