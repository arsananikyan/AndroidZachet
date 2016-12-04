package com.example.gtx660ti.myapplication.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.gtx660ti.myapplication.R;
import com.example.gtx660ti.myapplication.models.Song;

/**
 * Created by GTX660TI on 01.12.2016.
 */

public class PlayerFragment extends Fragment {

    private Song currentSong;
    private MediaPlayer mediaPlayer;
    private TextView songTitle;
    private ImageView playBtn;
    private View stopBtn;
    private SeekBar seekBar;
    private SeekBarThread seekBarThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.player_fragment_layout, container, false);
        playBtn = (ImageView) content.findViewById(R.id.play_btn);
        stopBtn = content.findViewById(R.id.stop_btn);
        songTitle = (TextView) content.findViewById(R.id.textView);
        seekBar = (SeekBar) content.findViewById(R.id.seek_bar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
                if (seekBar.getMax() == progress) {
                    stop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null) {
                    if (currentSong != null)
                        play(currentSong);
                    return;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playBtn.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                } else {
                    mediaPlayer.start();
                    playBtn.setImageResource(R.drawable.ic_pause_white_48dp);
                }
            }
        });

        return content;
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
            seekBar.setProgress(0);
            seekBarThread.interrupt();
            playBtn.setImageResource(R.drawable.ic_play_arrow_white_48dp);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }


    public void play(Song song) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        if (seekBarThread != null) {
            seekBarThread.interrupt();
        }
        mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(song.getAbsolutePath()));
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        seekBar.setProgress(0);
        currentSong = song;
        seekBarThread = new SeekBarThread();
        songTitle.setText(song.getName());
        seekBarThread.start();
        mediaPlayer.start();
        playBtn.setImageResource(R.drawable.ic_pause_white_48dp);
    }

    private class SeekBarThread extends Thread {
        @Override
        public void run() {

            try {
                while (!Thread.interrupted()) {

                    if (mediaPlayer != null) {
                        int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        Log.e("PlayerFragment", currentPosition + "");
                        seekBar.setProgress(currentPosition);
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}

