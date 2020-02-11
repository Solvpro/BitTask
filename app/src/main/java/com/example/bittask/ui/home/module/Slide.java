package com.example.bittask.ui.home.module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Slide {

    private int imageId;

    private String imageTitle;

    private String imageSubTitle;

    public Slide(int imageId, String imageTitle, String imageSubTitle) {
        this.imageId = imageId;
        this.imageTitle = imageTitle;
        this.imageSubTitle = imageSubTitle;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageSubTitle() {
        return imageSubTitle;
    }
}
