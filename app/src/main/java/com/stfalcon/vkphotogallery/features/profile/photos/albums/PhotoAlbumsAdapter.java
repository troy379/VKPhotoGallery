package com.stfalcon.vkphotogallery.features.profile.photos.albums;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.common.model.PhotoAlbum;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by troy379 on 26.12.16.
 */
public class PhotoAlbumsAdapter
        extends RecyclerView.Adapter<PhotoAlbumsAdapter.AlbumViewHolder> {

    private List<PhotoAlbum> photoAlbums = new ArrayList<>();

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumViewHolder(
                View.inflate(parent.getContext(), R.layout.item_photo_album, parent));
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.sdvThumb.setImageURI(photoAlbums.get(position).getThumb());
    }

    @Override
    public int getItemCount() {
        return photoAlbums.size();
    }

    public void add(List<PhotoAlbum> albums) {
        photoAlbums.addAll(albums);
        notifyDataSetChanged();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView sdvThumb;

        AlbumViewHolder(View itemView) {
            super(itemView);

            sdvThumb = (SimpleDraweeView) itemView.findViewById(R.id.sdvThumb);
        }
    }
}
