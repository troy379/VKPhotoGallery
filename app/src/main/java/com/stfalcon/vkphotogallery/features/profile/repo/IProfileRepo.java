package com.stfalcon.vkphotogallery.features.profile.repo;/*
 * Created by troy379 on 11.01.17.
 */

import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;
import com.stfalcon.vkphotogallery.common.model.user.User;
import com.stfalcon.vkphotogallery.common.repo.Repo;

public interface IProfileRepo {

    void getProfile(long id,
                    Repo.Result<User> onSuccess,
                    Repo.Result<VkError> onError);

}
