package com.amirely.elite.bakit.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.amirely.elite.bakit.R;

public class Navigator {

    private FragmentManager fragmentManager;

    public Navigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    public void navigateTo(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
