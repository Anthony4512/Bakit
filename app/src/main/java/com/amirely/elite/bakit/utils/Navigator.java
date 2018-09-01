package com.amirely.elite.bakit.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.amirely.elite.bakit.R;

public class Navigator {

    private FragmentManager fragmentManager;

    public Navigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }



    public void createMainFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack("main_fragment")
                .commit();
    }

    public void navigateTo(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    public void makeStepsFullScreen(Fragment fragment1, Fragment fragment2) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment1)
                .addToBackStack(null)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.secondary_fragment_container, fragment2)
                .addToBackStack(null)
                .commit();
    }


    public void addSecondFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.secondary_fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }
}
