package com.stfalcon.vkphotogallery.features.profile.photos.all;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.common.model.Photo;

import java.util.List;

/*
 * Created by troy379 on 26.12.16.
 */
public class AllPhotosFragment extends Fragment {

    private RecyclerView recyclerView;
    private AllPhotosAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_albums, container);

        this.recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        return v;
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.adapter = new AllPhotosAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void onAlbumsLoaded(List<Photo> albums) {
        adapter.add(albums);
    }
}
