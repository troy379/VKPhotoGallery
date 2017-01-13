package com.stfalcon.vkphotogallery.features.profile.photos.all.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.common.model.photos.Photo;
import com.stfalcon.vkphotogallery.common.repo.Repo;
import com.stfalcon.vkphotogallery.features.prefs.Preferences;
import com.stfalcon.vkphotogallery.features.profile.photos.all.repo.IAllPhotosRepo;
import com.stfalcon.vkphotogallery.features.profile.photos.all.repo.RetrofitAllPhotosRepo;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by troy379 on 26.12.16.
 */
public class AllPhotosFragment extends Fragment {

    private RecyclerView recyclerView;
    private AllPhotosAdapter adapter;
    private IAllPhotosRepo allPhotosRepo = new RetrofitAllPhotosRepo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_albums, container, false);

        this.recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        initRecycler();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        allPhotosRepo.getAllPhotos(
                Preferences.with(getActivity()).getUser(), 0,
                new Repo.Result<ArrayList<Photo>>() {
                    @Override
                    public void response(ArrayList<Photo> photos) {
                        onAlbumsLoaded(photos);
                    }
                }, new Repo.Result<Throwable>() {
                    @Override
                    public void response(Throwable throwable) {

                    }
                }
        );
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.adapter = new AllPhotosAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void onAlbumsLoaded(List<Photo> albums) {
        adapter.add(albums);
    }
}
