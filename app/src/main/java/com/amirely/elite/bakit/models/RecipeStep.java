package com.amirely.elite.bakit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeStep {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

}


//    private String id;
//    private String shortDescription;
//    private String description;
//    private String videoURL;
//    private String thumbnailURL;
//
//
//    public RecipeStep(String id, String shortDescription, String description, String videoURL, String thumbnailURL) {
//        this.id = id;
//        this.shortDescription = shortDescription;
//        this.description = description;
//        this.videoURL = videoURL;
//        this.thumbnailURL = thumbnailURL;
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
//    public String getShortDescription() {
//        return shortDescription;
//    }
//
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getVideoURL() {
//        return videoURL;
//    }
//
//    public void setVideoURL(String videoURL) {
//        this.videoURL = videoURL;
//    }
//
//    public String getThumbnailURL() {
//        return thumbnailURL;
//    }
//
//    public void setThumbnailURL(String thumbnailURL) {
//        this.thumbnailURL = thumbnailURL;
//    }
//}
