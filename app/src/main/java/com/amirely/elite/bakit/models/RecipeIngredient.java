package com.amirely.elite.bakit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeIngredient {

    @SerializedName("quantity")
    @Expose
    private double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}

//    private double quantity;
//    private String measure;
//    private String ingredient;
//
//    public RecipeIngredient(double quantity, String measure, String ingredient) {
//        this.quantity = quantity;
//        this.measure = measure;
//        this.ingredient = ingredient;
//    }
//
//
//    public double getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(double quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getMeasure() {
//        return measure;
//    }
//
//    public void setMeasure(String measure) {
//        this.measure = measure;
//    }
//
//    public String getIngredient() {
//        return ingredient;
//    }
//
//    public void setIngredient(String ingredient) {
//        this.ingredient = ingredient;
//    }
//}
