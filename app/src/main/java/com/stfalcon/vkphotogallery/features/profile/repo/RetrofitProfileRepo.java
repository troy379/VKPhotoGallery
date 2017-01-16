package com.stfalcon.vkphotogallery.features.profile.repo;

import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;
import com.stfalcon.vkphotogallery.common.model.reponses.user.UsersResponse;
import com.stfalcon.vkphotogallery.common.model.user.Field;
import com.stfalcon.vkphotogallery.common.model.user.NameCase;
import com.stfalcon.vkphotogallery.common.model.user.User;
import com.stfalcon.vkphotogallery.common.network.api.client.VkClient;
import com.stfalcon.vkphotogallery.common.network.api.services.UsersService;
import com.stfalcon.vkphotogallery.common.repo.RetrofitRepo;

/*
 * Created by troy379 on 11.01.17.
 */
public class RetrofitProfileRepo extends RetrofitRepo
        implements IProfileRepo {

    private UsersService usersService;

    public RetrofitProfileRepo() {
        this.usersService = VkClient.makeService(UsersService.class);
    }

    @Override
    public void getProfile(long id, final Result<User> onSuccess, final Result<VkError> onError) {
        usersService.get(
                new long[]{id},
                new Field[]{
                        Field.PHOTO_MAX_ORIG,
                        Field.STATUS,
                        Field.COUNTERS,
                },
                NameCase.NOMINATIVE)
                .enqueue(new VkCallback<>(new Result<UsersResponse>() {
                    @Override
                    public void response(UsersResponse usersResponse) {
                        onSuccess.response(usersResponse.getFirst());
                    }
                }, onError));
    }
}
