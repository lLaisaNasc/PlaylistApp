package com.example.playlistapp.ui.views

import android.provider.MediaStore.Audio.Artists
import com.example.playlistapp.models.Song

data class SongListUIState(
    val Allsongs: List<Song> = listOf()
)
