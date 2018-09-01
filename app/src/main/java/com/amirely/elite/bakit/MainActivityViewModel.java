package com.amirely.elite.bakit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeStep;
import com.amirely.elite.bakit.network.NetworkService;
import com.amirely.elite.bakit.network.RecipeApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel{

    private MutableLiveData<List<Recipe>> recipeList;
    private RecipeApi recipeApi;

    public MainActivityViewModel() {
        recipeApi = NetworkService.create(RecipeApi.class);
    }

    public LiveData<List<Recipe>> getRecipeList() {
        if(recipeList == null) {
            recipeList = new MutableLiveData<>();
            fetchRecipeList();
        }
        return recipeList;
    }

    private void fetchRecipeList() {

        Log.d("FETCH MOVIES", "Fetch movies has been called");
        Call<Recipe[]> searchMoviesByQueryCall;

        searchMoviesByQueryCall = recipeApi.getListRecipes();

        searchMoviesByQueryCall.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call, @NonNull Response<Recipe[]> response) {
                Recipe[] recipeResults = response.body();
                List<Recipe> receivedRecipeList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(recipeResults)));

                recipeList.postValue(receivedRecipeList);

                Log.d("RESPONSE", String.valueOf(receivedRecipeList.size()));
            }

            @Override
            public void onFailure(@NonNull Call<Recipe[]> call, Throwable t) {
                Log.d("RECIPES FRAGMENT 2", t.getMessage());
            }
        });
    }

}
