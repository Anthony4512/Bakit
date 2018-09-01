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
import android.widget.LinearLayout;

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

    private boolean isTablet;



    public RecipesFragment() {
        // Required empty public constructor
        recipeApi = NetworkService.create(RecipeApi.class);
    }

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    public static RecipesFragment newInstance(boolean isTablet) {

        RecipesFragment fragment = new RecipesFragment();
        fragment.isTablet = isTablet;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        boolean isTablet = container.findViewById(R.id.tablet_main_layout) != null;


//        if(isTablet) {
//            LinearLayout parent = (LinearLayout) container;
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    MATCH_PARENT,
//                    MATCH_PARENT);
//
//            parent.setLayoutParams(params);
//        }


        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipes_rv);

        initializeView();

        model.getRecipeList().observe(this, recipes -> {
            recipeList = recipes;
            RecipeAdapter recipeAdapter = new RecipeAdapter(recipeList, RecipesFragment.this);
            recipeRecyclerView.setAdapter(recipeAdapter);
        });

        return view;
    }


    public void initializeView() {
        if(isTablet && Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {

            LinearLayout parent = getActivity().findViewById(R.id.main_fragment_container);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    MATCH_PARENT,
                    MATCH_PARENT);

            parent.setLayoutParams(params);
        }


        if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE || isTablet) {


            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
            recipeRecyclerView.setLayoutManager(layoutManager);

        }
        else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recipeRecyclerView.setLayoutManager(layoutManager);
        }
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
        initializeView();

    }
}
