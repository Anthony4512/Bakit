package com.amirely.elite.bakit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<RecipeIngredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private List<RecipeStep> steps = null;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}


//    private String id;
//    private String name;
//    private List<RecipeIngredient> ingredients;
//    @SerializedName("steps")
//    private List<RecipeStep> steps;
//    private int servings;
//    private String image;
//
//    public Recipe(String id, String name, List<RecipeIngredient> ingredients, List<RecipeStep> steps, int servings, String image) {
//        this.id = id;
//        this.name = name;
//        this.ingredients = ingredients;
//        this.steps = steps;
//        this.servings = servings;
//        this.image = image;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<RecipeIngredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<RecipeIngredient> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public List<RecipeStep> getSteps() {
//        return steps;
//    }
//
//    public void setSteps(List<RecipeStep> steps) {
//        this.steps = steps;
//    }
//
//    public int getServings() {
//        return servings;
//    }
//
//    public void setServings(int servings) {
//        this.servings = servings;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//}
