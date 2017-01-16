package com.stfalcon.vkphotogallery.features.profile.photos.all.repo;

import com.stfalcon.vkphotogallery.common.model.photos.Photo;
import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;
import com.stfalcon.vkphotogallery.common.repo.Repo;

import java.util.ArrayList;

/*
 * Created by troy379 on 13.01.17.
 */
public interface IAllPhotosRepo {

    void getAllPhotos(long id, int offset,
                      final Repo.Result<ArrayList<Photo>> onSuccess,
                      final Repo.Result<VkError> onError);
}
