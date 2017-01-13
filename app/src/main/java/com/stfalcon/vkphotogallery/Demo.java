package com.stfalcon.vkphotogallery;

import com.stfalcon.vkphotogallery.common.model.PhotoAlbum;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by troy379 on 26.12.16.
 */
public final class Demo {
    private Demo() {
        throw new AssertionError();
    }

    public static List<PhotoAlbum> getPhotoAlbums() {
        List<PhotoAlbum> albums = new ArrayList<>();

        for (int i = 0; i < 400; i++) {
            albums.add(new PhotoAlbum("http://cache.gawkerassets.com/assets/images/lifehacker/2011/10/1200-questioning.jpg"));
        }

        return albums;
    }
}
