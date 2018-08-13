package com.amirely.elite.bakit.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amirely.elite.bakit.R;
import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeIngredient;
import com.amirely.elite.bakit.models.RecipeStep;

import java.util.ArrayList;
import java.util.List;



public class RecipesFragment extends Fragment implements RecipeAdapter.OnRecipeClickListener{
//    private OnFragmentInteractionListener mListener;

    List<Recipe> recipeList;

    RecyclerView recipeRecyclerView;


    public RecipesFragment() {
        // Required empty public constructor
    }

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void pupulateRecipeList() {

        for (int i = 0; i < 10; i++) {

            RecipeIngredient ingredient = new RecipeIngredient(1.5, "measure", "INGREDIENT"+i);
            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
            recipeIngredients.add(ingredient);

            RecipeStep recipeStep = new RecipeStep("step1"+i, "this is a short description", "another description", "http//videourl", "http//thumbnail");
            ArrayList<RecipeStep> steps = new ArrayList<>();
            steps.add(recipeStep);

            Recipe recipe = new Recipe("1", "RecipeName"+i, recipeIngredients, steps, 1, "http//imageForRecipe");
            recipeList.add(recipe);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        recipeList = new ArrayList<>();

        pupulateRecipeList();

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipes_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);

        RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, this);

        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setAdapter(recipeAdapter);

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
    public void onRecipeClicked(Recipe recipe) {
        Log.d("ON RECIPE", recipe.getName() + " has been clicked");
    }
}
