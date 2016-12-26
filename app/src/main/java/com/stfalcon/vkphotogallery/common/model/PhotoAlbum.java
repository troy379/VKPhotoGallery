package com.stfalcon.vkphotogallery.common.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by troy379 on 26.12.16.
 */
public class PhotoAlbum {

    @SerializedName("thumb")
    private String thumb;

    public String getThumb() {
        return thumb;
    }
}
