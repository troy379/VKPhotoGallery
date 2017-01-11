package com.stfalcon.vkphotogallery.common.model.reponses.base;

import com.google.gson.annotations.SerializedName;

/*
 * Created by troy379 on 28.12.16.
 */
public abstract class VkResponse<T> {

    @SerializedName("response")
    private T response;

    @SerializedName("error")
    private Error error;

    public T getResponse() {
        return response;
    }

    public boolean isSuccessfull() {
        return error == null;
    }

    public Error getError() {
        return error;
    }
}
