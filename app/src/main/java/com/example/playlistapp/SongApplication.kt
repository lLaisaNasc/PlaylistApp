package com.example.playlistapp

import android.app.Application
import com.example.playlistapp.models.PlaylistRoomDatabase

class SongApplication: Application() {

    val database: PlaylistRoomDatabase by lazy {
        PlaylistRoomDatabase.getDatabase(this)
    }
}