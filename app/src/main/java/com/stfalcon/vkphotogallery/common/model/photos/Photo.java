package com.stfalcon.vkphotogallery.common.model.photos;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/*
 * Created by troy379 on 13.01.17.
 */
public class Photo {

    @SerializedName("id")
    private long id;

    @SerializedName("album_id")
    private long albumId;

    @SerializedName("owner_id")
    private long ownerId;

    @SerializedName("photo_75")
    private String photo75;

    @SerializedName("photo_130")
    private String photo130;

    @SerializedName("photo_604")
    private String photo604;

    @SerializedName("photo_807")
    private String photo807;

    @SerializedName("photo_1280")
    private String photo1280;

    @SerializedName("photo_2560")
    private String photo2560;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private int date;

    @SerializedName("post_id")
    private int postId;

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("reposts")
    private Reposts reposts;

    public long getId() {
        return id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getPhoto75() {
        return photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public String getPhoto1280() {
        return photo1280;
    }

    public String getPhoto2560() {
        return photo2560;
    }

    public String getLargestPhoto() {
        if (photo2560 != null) return photo2560;
        if (photo1280 != null) return photo1280;
        if (photo807 != null) return photo807;
        if (photo604 != null) return photo604;
        if (photo130 != null) return photo130;
        return photo75;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return new Date(date);
    }

    public int getPostId() {
        return postId;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public static class Likes {

        @SerializedName("user_likes")
        private int userLikes;

        @SerializedName("count")
        private int count;

        public int getUserLikes() {
            return userLikes;
        }

        public int getCount() {
            return count;
        }
    }

    public static class Reposts {

        @SerializedName("count")
        private int count;

        public int getCount() {
            return count;
        }
    }
}
