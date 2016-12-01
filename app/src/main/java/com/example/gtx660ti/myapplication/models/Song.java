package com.example.gtx660ti.myapplication.models;

import java.util.ArrayList;

/**
 * Created by GTX660TI on 01.12.2016.
 */

public class Song {
    private String name;
    private String absolutePath;

    public Song(String absolutePath) {
        this.absolutePath = absolutePath;
        name = absolutePath.substring(absolutePath.lastIndexOf("/") + 1, absolutePath.length() - 4);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public static ArrayList<Song> toSongs(ArrayList<String> paths) {
        ArrayList<Song> songs = new ArrayList<>();
        for (String path : paths) {
            songs.add(new Song(path));
        }
        return songs;
    }
}
