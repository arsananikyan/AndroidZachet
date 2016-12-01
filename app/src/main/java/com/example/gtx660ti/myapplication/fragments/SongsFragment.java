package com.example.gtx660ti.myapplication.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.gtx660ti.myapplication.R;
import com.example.gtx660ti.myapplication.adapters.SongsAdapter;
import com.example.gtx660ti.myapplication.models.Song;
import com.example.gtx660ti.myapplication.utils.Utils;

import java.util.ArrayList;

/**
 * Created by GTX660TI on 28.11.2016.
 */

public class SongsFragment extends Fragment {

    private boolean isPlayerVisible = false;
    private RecyclerView recyclerView;
    private SongsAdapter adapter;
    private PlayerFragment playerFragment;
    private View playerFragmentLayout;

    private ArrayList<String> songs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.songs_fragment_layout, container, false);
        recyclerView = (RecyclerView) content.findViewById(R.id.songs_recycler_view);
        playerFragmentLayout = content.findViewById(R.id.player_fragment_layout);
        getMusic();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SongsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setSongs(Song.toSongs(songs));
        adapter.setOnItemClickListener(new SongsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Song song, int position) {
                animatePlayer(!isPlayerVisible, position);
            }
        });
        playerFragment = new PlayerFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.player_fragment_layout, playerFragment).commit();
        return content;
    }

    private void animatePlayer(boolean isShow, final int position) {
        isPlayerVisible = isShow;
        ValueAnimator valueAnimator;
        if (isShow) {
            valueAnimator = ValueAnimator.ofInt(-(int) Utils.convertDPtoPX(getActivity(), 200), 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, -(int) Utils.convertDPtoPX(getActivity(), 200));
        }

        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) playerFragmentLayout.getLayoutParams();
                layoutParams.bottomMargin = value;
                playerFragmentLayout.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                recyclerView.smoothScrollToPosition(position);
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getMusic() {
        ContentResolver cr = getActivity().getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;

        if (cur != null) {
            count = cur.getCount();

            if (count > 0) {
                while (cur.moveToNext()) {
                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    if (data.contains(".mp3")) {
                        songs.add(data);
                    }
                }
            }
        }
        cur.close();
    }
}

