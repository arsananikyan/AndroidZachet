package com.example.gtx660ti.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gtx660ti.myapplication.R;
import com.example.gtx660ti.myapplication.models.Song;

import java.util.ArrayList;

/**
 * Created by GTX660TI on 01.12.2016.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder> {

    private ArrayList<Song> songs;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public SongsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = new ArrayList<>(songs);
        notifyDataSetChanged();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(inflater.inflate(R.layout.item_song, parent, false));
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, final int position) {
        final Song song = songs.get(position);
        holder.songName.setText(song.getName());
        holder.songName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(song, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView songName;

        public SongViewHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.song_name_text_view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(Song song, int position);
    }
}
