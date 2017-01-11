package com.stfalcon.vkphotogallery.common.model.reponses.errors;

import com.google.gson.annotations.SerializedName;

/*
 * Created by troy379 on 28.12.16.
 */
public class Error {

    @SerializedName("error_code")
    private int errorCode;

    @SerializedName("error_msg")
    private String errorMesssage;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMesssage() {
        return errorMesssage;
    }
}
