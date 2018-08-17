package com.amirely.elite.bakit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.amirely.elite.bakit.homepage.RecipesFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, RecipesFragment.newInstance())
                    .commit();

        }

    }
}
