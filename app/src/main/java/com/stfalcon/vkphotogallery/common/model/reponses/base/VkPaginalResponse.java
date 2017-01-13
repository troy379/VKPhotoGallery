package com.stfalcon.vkphotogallery.common.model.reponses.base;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by troy379 on 28.12.16.
 */
public class VkPaginalResponse<T> extends VkResponse<VkPaginalResponse<T>> {

    @SerializedName("count")
    private int count;

    @SerializedName("items")
    private ArrayList<T> items;

    public int getCount() {
        return count;
    }

    public ArrayList<T> getItems() {
        return items;
    }
}
