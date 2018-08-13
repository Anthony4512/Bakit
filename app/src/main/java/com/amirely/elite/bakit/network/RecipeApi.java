package com.amirely.elite.bakit.network;



import com.amirely.elite.bakit.models.Recipe;
import com.amirely.elite.bakit.models.RecipeResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<RecipeResults> getRecipes();

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<Recipe[]> getListRecipes();




//    @GET("movie/top_rated")
//    Call<MovieList> getTopRatedMovies();
//
//    @GET("movie/upcoming")
//    Call<MovieList> getUpcomingMovies();
//
//    @GET("search/multi")
//    Call<SearchResults> searchMulti(@Query("query") String query);
//
//    @GET("search/movie")
//    Call<SearchResults> searchMovie(@Query("query") String query);
//
//    @GET("search/person")
//    Call<SearchResults> searchPeople(@Query("query") String query);
//
//    @GET("search/tv")
//    Call<SearchResults> searchTvShow(@Query("query") String query);
//
//    @GET("person/{personId}")
//    Call<SearchResult> getPersonDetails(@Path("personId") String personId);
}

