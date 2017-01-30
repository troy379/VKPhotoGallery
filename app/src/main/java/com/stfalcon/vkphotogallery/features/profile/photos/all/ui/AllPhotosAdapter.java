package com.stfalcon.vkphotogallery.features.profile.photos.all.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.common.model.photos.Photo;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by troy379 on 26.12.16.
 */
public class AllPhotosAdapter extends RecyclerView.Adapter<AllPhotosAdapter.PhotoViewHolder> {

    private List<Photo> photos = new ArrayList<>();

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.sdvThumb.setImageURI(photos.get(position).getLargestPhoto());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void add(List<Photo> albums) {
        photos.addAll(albums);
        notifyDataSetChanged();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private SimpleDraweeView sdvThumb;

        PhotoViewHolder(View itemView) {
            super(itemView);

            sdvThumb = (SimpleDraweeView) itemView.findViewById(R.id.sdvThumb);
            sdvThumb.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ArrayList<String> urls = new ArrayList<>();
            for (Photo photo : photos) {
                urls.add(photo.getLargestPhoto());
            }

            new ImageViewer.Builder(view.getContext(), urls)
                    .setStartPosition(getAdapterPosition())
                    .show();
        }
    }
}
