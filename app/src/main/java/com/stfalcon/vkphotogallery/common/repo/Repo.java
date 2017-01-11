package com.stfalcon.vkphotogallery.common.repo;

/*
 * Created by troy379 on 11.01.17.
 */
public abstract class Repo {

    public interface Result<T> {
        void response(T t);
    }
}
