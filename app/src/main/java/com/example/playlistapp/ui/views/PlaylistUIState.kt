package com.example.playlistapp.ui.views

import com.example.playlistapp.models.Song

data class PlaylistUIState(
    val playlist: List<Song> = listOf()
)
