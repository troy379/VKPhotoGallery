package com.stfalcon.vkphotogallery.common.repo;

import com.stfalcon.vkphotogallery.common.events.AccessTokenHasExpiredEvent;
import com.stfalcon.vkphotogallery.common.model.reponses.base.VkResponse;
import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by troy379 on 16.01.17.
 */
public class RetrofitRepo extends Repo {

    public static class VkCallback<T extends VkResponse> implements Callback<T> {

        private static final int ERROR_CODE_UNAUTHORIZED = 5;

        private Result<T> onSuccess;
        private Result<VkError> onError;

        public VkCallback(Result<T> onSuccess, Result<VkError> onError) {
            this.onSuccess = onSuccess;
            this.onError = onError;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.body().isSuccessful()) {
                onSuccess.response(response.body());
            } else handleError(response.body().getError());
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            // TODO: 16.01.17 save call, clone and make request after auth
        }

        private void handleError(VkError error) {
            switch (error.getErrorCode()) {
                case ERROR_CODE_UNAUTHORIZED:
                    EventBus.getDefault().post(new AccessTokenHasExpiredEvent());
                    // TODO: 16.01.17 save call, clone and make request after auth
                    break;
                default: onError.response(error);
            }
        }
    }
}
