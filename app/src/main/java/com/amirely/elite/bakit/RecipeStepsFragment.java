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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeIngredient;
import com.amirely.elite.bakit.models.RecipeStep;
import com.amirely.elite.bakit.utils.Navigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class RecipeStepsFragment extends Fragment implements StepsAdapter.OnStepClickListener {
    //    private OnFragmentInteractionListener mListener;

    ArrayList<RecipeStep> stepList;
    List<RecipeIngredient> ingredients;
    Recipe currentRecipe;

    RecyclerView recipeRecyclerView;

    Navigator navigator;

    int stepPosition;

    FragmentManager manager;

    boolean isTablet;

    boolean isPortrait;

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
        isTablet = Objects.requireNonNull(getActivity()).findViewById(R.id.tablet_main_layout) != null;
        isPortrait = Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        manager = getFragmentManager();

        navigator = new Navigator(manager);

        if(savedInstanceState != null) {
            stepList = savedInstanceState.getParcelableArrayList("stepList");
            currentRecipe = savedInstanceState.getParcelable("recipe");
            isTablet = savedInstanceState.getBoolean("isTablet");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






//        if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
//                Configuration.ORIENTATION_LANDSCAPE) {
//            navigator.makeStepsFullScreen(this, RecipeStepDetailsFragment.newInstance(currentRecipe.getSteps(), stepPosition));
//        }
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

        resizeScreen();
        if(!isPortrait && isTablet) {
            navigator.addSecondFragment(RecipeStepDetailsFragment.newInstance(currentRecipe, stepPosition, isTablet));

        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        stepList = null;
        ingredients = null;
        currentRecipe = null;

        recipeRecyclerView = null;

        navigator = null;

        manager = null;
    }

    @Override
    public void onStepClicked(int position) {

        this.stepPosition = position;
        if(isTablet && !isPortrait) {
            resizeScreen();
            navigator.addSecondFragment(RecipeStepDetailsFragment.newInstance(currentRecipe, position, isTablet));
        }
        else {
            navigator.navigateTo(RecipeStepDetailsFragment.newInstance(currentRecipe, position, isTablet));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("stepsList", stepList);
        outState.putParcelable("recipe", currentRecipe);
        outState.putBoolean("isTablet", isTablet);
    }


    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getActivity()).setTitle(currentRecipe.getName());

    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);



        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            isPortrait = false;

            resizeScreen();

            Log.d("ORIENTATION", "LANDSCAPE");

            if(isTablet) {
                Log.d("ORIENTATION", "LANDSCAPE TABLET");



                if(currentRecipe != null) {
                    navigator.addSecondFragment(RecipeStepDetailsFragment.newInstance(currentRecipe, stepPosition, isTablet));
                }

            }

            Toast.makeText(getActivity(), "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            isPortrait = true;

            resizeScreen();

            Log.d("ORIENTATION", "PORTRAIT");
            Toast.makeText(getActivity(), "portrait", Toast.LENGTH_SHORT).show();
        }
    }


    public void resizeScreen() {
        if(isTablet) {
            if (isPortrait) {

                LinearLayout parent = Objects.requireNonNull(getActivity()).findViewById(R.id.main_fragment_container);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        MATCH_PARENT,
                        MATCH_PARENT);
                parent.setLayoutParams(params);
            } else {
                LinearLayout parent = Objects.requireNonNull(getActivity()).findViewById(R.id.main_fragment_container);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        800,
                        MATCH_PARENT);
                parent.setLayoutParams(params);

            }
        }
    }
}