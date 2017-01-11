package com.stfalcon.vkphotogallery.features.profile.repo;

import com.stfalcon.vkphotogallery.common.model.reponses.user.UsersResponse;
import com.stfalcon.vkphotogallery.common.model.user.Field;
import com.stfalcon.vkphotogallery.common.model.user.NameCase;
import com.stfalcon.vkphotogallery.common.model.user.User;
import com.stfalcon.vkphotogallery.common.network.api.client.VkClient;
import com.stfalcon.vkphotogallery.common.network.api.services.UsersService;
import com.stfalcon.vkphotogallery.common.repo.Repo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by troy379 on 11.01.17.
 */
public class RetrofitProfileRepo extends Repo
        implements IProfileRepo {

    private UsersService usersService;

    public RetrofitProfileRepo() {
        this.usersService = VkClient.makeService(UsersService.class);
    }

    @Override
    public void getProfile(long id, final Result<User> onSuccess, final Result<Throwable> onError) {
        usersService.get(
                new long[]{id},
                new Field[]{
                        Field.PHOTO_MAX_ORIG,
                        Field.STATUS,
                        Field.COUNTERS,
                },
                NameCase.NOMINATIVE)
                .enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        onSuccess.response(response.body().getFirst());
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        onError.response(t);
                    }
                });
    }
}
