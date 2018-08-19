package com.amirely.elite.bakit.homepage;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.amirely.elite.bakit.MainActivityViewModel;
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

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class RecipesFragment extends Fragment implements RecipeAdapter.OnRecipeClickListener {

    List<Recipe> recipeList;

    RecyclerView recipeRecyclerView;

    RecipeApi recipeApi;

    Navigator navigator;

    FragmentManager manager;

    MainActivityViewModel model;



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

        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipes_rv);


        if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager layoutManager = new GridLayoutManager(container.getContext(), 3, LinearLayoutManager.VERTICAL, false);
            recipeRecyclerView.setLayoutManager(layoutManager);

        }
        else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
            recipeRecyclerView.setLayoutManager(layoutManager);
        }



        model.getRecipeList().observe(this, recipes -> {
            recipeList = recipes;
            RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, RecipesFragment.this);
            recipeRecyclerView.setAdapter(recipeAdapter);
        });

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
        manager = getFragmentManager();
        navigator = new Navigator(manager);
        navigator.navigateTo(RecipeStepsFragment.newInstance(recipe));
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle("Bakit");

    }
}
