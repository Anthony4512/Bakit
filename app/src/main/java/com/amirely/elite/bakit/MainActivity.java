package com.amirely.elite.bakit;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.amirely.elite.bakit.homepage.RecipesFragment;
import com.amirely.elite.bakit.utils.Navigator;

import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    Navigator navigator;
    private boolean isTablet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator = new Navigator(getSupportFragmentManager());

        isTablet = findViewById(R.id.tablet_main_layout) != null;

        if(savedInstanceState == null) {

//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.full_screen_fragment_container, RecipesFragment.newInstance())
//                    .addToBackStack(null)
//                    .commit();

            navigator.createMainFragment(RecipesFragment.newInstance(isTablet));


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        if(isTablet && Objects.requireNonNull(getResources().getConfiguration().orientation ==
//                Configuration.ORIENTATION_LANDSCAPE)) {
//
//            getSupportFragmentManager().popBackStack("main_fragment", 0);
//
//        }

        if(getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        }
        else {
            getSupportFragmentManager().popBackStack("main_fragment", 0);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isTablet = findViewById(R.id.tablet_main_layout) != null;
    }
}
