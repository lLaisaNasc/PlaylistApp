package com.example.playlistapp.ui.views

import android.provider.MediaStore.Audio.Artists
import com.example.playlistapp.models.Artist

data class ArtistListUIState(
    val allArtists: List<Artist> = listOf()
)
