package com.stfalcon.vkphotogallery.common.model.reponses.base;

import com.google.gson.annotations.SerializedName;
import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;

/*
 * Created by troy379 on 28.12.16.
 */
public abstract class VkResponse<T> {

    @SerializedName("response")
    private T response;

    @SerializedName("error")
    private VkError error;

    public T getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return error == null;
    }

    public VkError getError() {
        return error;
    }
}
