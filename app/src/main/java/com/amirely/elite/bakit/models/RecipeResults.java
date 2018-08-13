package com.amirely.elite.bakit.models;

public class RecipeResults {

    private Recipe[] results;

    public RecipeResults(Recipe[] movieSearchList) {
        this.results = movieSearchList;
    }

    public Recipe[] getResults() {
        return results;
    }
}
