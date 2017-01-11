package com.stfalcon.vkphotogallery.common.model.reponses.user;

import com.stfalcon.vkphotogallery.common.model.reponses.base.VkResponse;
import com.stfalcon.vkphotogallery.common.model.user.User;

import java.util.ArrayList;

/*
 * Created by troy379 on 11.01.17.
 */
public class UsersResponse extends VkResponse<ArrayList<User>> {

    public User getFirst() {
        return getResponse().get(0);
    }
}
