package com.amirely.elite.bakit;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.amirely.elite.bakit.homepage.RecipesFragment;
import com.amirely.elite.bakit.utils.Navigator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    Navigator navigator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator = new Navigator(getSupportFragmentManager());

        boolean isTablet = findViewById(R.id.tablet_main_layout) != null;

        if(savedInstanceState == null) {

//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.full_screen_fragment_container, RecipesFragment.newInstance())
//                    .addToBackStack(null)
//                    .commit();

            navigator.navigateTo(RecipesFragment.newInstance(isTablet));


        }
    }

}
