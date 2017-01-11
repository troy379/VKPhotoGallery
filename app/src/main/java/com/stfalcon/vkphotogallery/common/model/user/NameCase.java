package com.stfalcon.vkphotogallery.common.model.user;

import com.google.gson.annotations.SerializedName;

/*
 * Created by troy379 on 11.01.17.
 */
public enum NameCase {

    @SerializedName("nom")
    NOMINATIVE,

    @SerializedName("gen")
    GENITIVE,

    @SerializedName("dat")
    DATIVE,

    @SerializedName("acc")
    ACCUSATIVE,

    @SerializedName("ins")
    ABLATIVE,

    @SerializedName("abl")
    PREPOSITIONAL
}
