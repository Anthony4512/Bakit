package com.amirely.elite.bakit.homepage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amirely.elite.bakit.R;
import com.amirely.elite.bakit.RecipeStepsFragment;
import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.network.NetworkService;
import com.amirely.elite.bakit.network.RecipeApi;
import com.amirely.elite.bakit.utils.Navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipesFragment extends Fragment implements RecipeAdapter.OnRecipeClickListener{



    List<Recipe> recipeList;

    RecyclerView recipeRecyclerView;

    RecipeApi recipeApi;

    Navigator navigator;

    FragmentManager manager;


    public RecipesFragment() {
        // Required empty public constructor
        recipeApi = NetworkService.create(RecipeApi.class);
    }

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        recipeList = new ArrayList<>();


//        List<Recipe> recipeList = (List<Recipe>) recipeApi.getRecipes();

        Log.d("LIST OF RECIPES", String.valueOf(recipeList.size()));

//        populateRecipeList();

        fetchRecipeList();

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipes_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);

        RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, this);

        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setAdapter(recipeAdapter);

        return view;
    }

    public void fetchRecipeList() {
        Call<Recipe[]> searchMoviesByQueryCall;

        searchMoviesByQueryCall = recipeApi.getListRecipes();


        searchMoviesByQueryCall.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call, @NonNull Response<Recipe[]> response) {
                Recipe[] recipeResults = response.body();
                List<Recipe> recivedRecipeList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(recipeResults)));


                updateAdapter(recivedRecipeList);


                Log.d("RESPONSE", String.valueOf(recivedRecipeList.size()));
            }
            @Override
            public void onFailure(@NonNull Call<Recipe[]> call, Throwable t) {
                Log.d("RECIPES FRAGMENT 2", t.getMessage());
//                presenter.onFailure(t);
            }
        });
    }

    private void updateAdapter(List<Recipe> receivedRecipeList) {
        RecipeAdapter adapter = new RecipeAdapter(receivedRecipeList, this);

        recipeRecyclerView.swapAdapter(adapter, true);

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

        Log.d("ON RECIPE", String.valueOf(recipe.getSteps().size()) + " RECIPE STEPS");

        manager = getFragmentManager();

        navigator = new Navigator(manager);





        navigator.navigateTo(RecipeStepsFragment.newInstance(recipe.getSteps()));

    }

    public FragmentManager getCustomFragmentManager() {
        return getFragmentManager();
    }


    public void goBack() {
        Objects.requireNonNull(getFragmentManager()).popBackStack();
    }

}
