package com.stfalcon.vkphotogallery.features.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.common.model.reponses.errors.VkError;
import com.stfalcon.vkphotogallery.common.model.user.User;
import com.stfalcon.vkphotogallery.common.repo.Repo;
import com.stfalcon.vkphotogallery.features.geo.nearme.PhotosNearMeActivity;
import com.stfalcon.vkphotogallery.features.prefs.Preferences;
import com.stfalcon.vkphotogallery.features.profile.counters.CounterView;
import com.stfalcon.vkphotogallery.features.profile.photos.PhotosPagerAdapter;
import com.stfalcon.vkphotogallery.features.profile.repo.IProfileRepo;
import com.stfalcon.vkphotogallery.features.profile.repo.RetrofitProfileRepo;
import com.stfalcon.vkphotogallery.utils.AppUtils;

/*
 * Created by troy379 on 19.12.16.
 */
public class ProfileFragment extends Fragment
        implements Toolbar.OnMenuItemClickListener,
        View.OnClickListener {

    private Toolbar toolbar;

    private SimpleDraweeView sdvAvatar;

    private CounterView photosCounter;
    private CounterView friendsCounter;
    private CounterView followersCounter;

    private TextView tvFullName;
    private TextView tvStatus;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private IProfileRepo profileRepo = new RetrofitProfileRepo();

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.profile_menu);
        toolbar.setOnMenuItemClickListener(this);

        sdvAvatar = (SimpleDraweeView) v.findViewById(R.id.sdvAvatar);

        photosCounter = (CounterView) v.findViewById(R.id.photosCounter);
        friendsCounter = (CounterView) v.findViewById(R.id.friendsCounter);
        followersCounter = (CounterView) v.findViewById(R.id.followersCounter);

        tvFullName = (TextView) v.findViewById(R.id.tvFullName);
        tvStatus = (TextView) v.findViewById(R.id.tvStatus);

        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);

        viewPager.setAdapter(new PhotosPagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_by_location: {
                startActivity(new Intent(getActivity(), PhotosNearMeActivity.class));
            }
        }

        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        photosCounter.setTitle(R.string.profile_counter_title_photos);
        friendsCounter.setTitle(R.string.profile_counter_title_friends);
        followersCounter.setTitle(R.string.profile_counter_title_followers);

        loadProfile();
    }

    private void loadProfile() {
        profileRepo.getProfile(
                Preferences.with(getActivity()).getUser(),
                new Repo.Result<User>() {
                    @Override
                    public void response(User user) {
                        onProfileLoaded(user);
                    }
                }, new Repo.Result<VkError>() {
                    @Override
                    public void response(VkError error) {
                        AppUtils.makeToast(getActivity(), error.getErrorMesssage(), false);
                    }
                });
    }

    private void onProfileLoaded(User user) {
        if (user != null) {
            sdvAvatar.setImageURI(user.getPhoto());
            tvFullName.setText(user.getFullName());
            tvStatus.setText(user.getStatus());

            photosCounter.setCount(user.getCounters().getPhotos());
            friendsCounter.setCount(user.getCounters().getFriends());
            followersCounter.setCount(user.getCounters().getFollowers());
        }
    }
}
