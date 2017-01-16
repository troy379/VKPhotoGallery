package com.stfalcon.vkphotogallery.features.profile.photos.all.repo;

import com.stfalcon.vkphotogallery.common.model.photos.Photo;
import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;
import com.stfalcon.vkphotogallery.common.model.reponses.photos.AllPhotosResponse;
import com.stfalcon.vkphotogallery.common.network.api.client.VkClient;
import com.stfalcon.vkphotogallery.common.network.api.services.PhotosService;
import com.stfalcon.vkphotogallery.common.network.api.utils.BooleanInt;
import com.stfalcon.vkphotogallery.common.repo.Repo;
import com.stfalcon.vkphotogallery.common.repo.RetrofitRepo;

import java.util.ArrayList;

/*
 * Created by troy379 on 13.01.17.
 */
public class RetrofitAllPhotosRepo extends RetrofitRepo
        implements IAllPhotosRepo {

    private static final int PHOTOS_TO_LOAD = 200;

    private PhotosService photosService;

    public RetrofitAllPhotosRepo() {
        this.photosService = VkClient.makeService(PhotosService.class);
    }

    @Override
    public void getAllPhotos(long id, int offset,
                             final Repo.Result<ArrayList<Photo>> onSuccess,
                             final Repo.Result<VkError> onError) {
        photosService.getAll(
                id,
                BooleanInt.TRUE,
                BooleanInt.TRUE,
                offset,
                PHOTOS_TO_LOAD
        ).enqueue(new VkCallback<>(new Result<AllPhotosResponse>() {
            @Override
            public void response(AllPhotosResponse allPhotosResponse) {
                onSuccess.response(allPhotosResponse.getResponse().getItems());
            }
        }, onError));
    }
}
