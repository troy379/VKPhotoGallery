package com.stfalcon.vkphotogallery.features.profile.photos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stfalcon.vkphotogallery.features.profile.photos.albums.PhotoAlbumsFragment;
import com.stfalcon.vkphotogallery.features.profile.photos.all.AllPhotosFragment;

/*
 * Created by troy379 on 26.12.16.
 */
public class PhotosPagerAdapter extends FragmentStatePagerAdapter {

    public PhotosPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllPhotosFragment();
            case 1:
                return new PhotoAlbumsFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Albums";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
