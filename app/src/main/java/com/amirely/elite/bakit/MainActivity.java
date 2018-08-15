package com.amirely.elite.bakit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.amirely.elite.bakit.homepage.RecipesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager manager = getSupportFragmentManager();
        Fragment recipesFragment = RecipesFragment.newInstance();
//        Fragment stepsFragment = RecipeStepsFragment.newInstance();
//        Fragment recipeStepDetailsFragment = RecipeStepDetailsFragment.newInstance();


//        manager.beginTransaction().replace(R.id.main_fragment_container, stepsFragment).addToBackStack(null).commit();

//        manager.beginTransaction().replace(R.id.main_fragment_container, recipesFragment).addToBackStack(null).commit();

//        manager.beginTransaction().replace(R.id.main_fragment_container, recipeStepDetailsFragment).addToBackStack(null).commit();


        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment_container, RecipesFragment.newInstance())
                .commit();

    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
}
