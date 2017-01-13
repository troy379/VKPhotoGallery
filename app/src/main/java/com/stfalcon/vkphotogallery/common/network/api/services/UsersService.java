package com.stfalcon.vkphotogallery.common.network.api.services;

import com.stfalcon.vkphotogallery.common.model.reponses.user.UsersResponse;
import com.stfalcon.vkphotogallery.common.model.user.Field;
import com.stfalcon.vkphotogallery.common.model.user.NameCase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by troy379 on 16.12.16.
 */
public interface UsersService {

    @GET("users.get")
    Call<UsersResponse> get(
            @Query("user_ids") long[] ids,
            @Query("fields[]") Field[] fields,
            @Query("name_case") NameCase nameCase
    );
}
