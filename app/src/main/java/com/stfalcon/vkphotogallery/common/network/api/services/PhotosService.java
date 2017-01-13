package com.stfalcon.vkphotogallery.common.network.api.services;

import com.stfalcon.vkphotogallery.common.model.reponses.photos.AllPhotosResponse;
import com.stfalcon.vkphotogallery.common.network.api.utils.BooleanInt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by troy379 on 13.01.17.
 */
public interface PhotosService {

    @GET("photos.getAll")
    Call<AllPhotosResponse> getAll(
            @Query("owner_id") long ownerId,
            @Query("extended") BooleanInt extended,
            @Query("need_hidden") BooleanInt needHidden,
            @Query("offset") int offset,
            @Query("count") int count
    );
}
