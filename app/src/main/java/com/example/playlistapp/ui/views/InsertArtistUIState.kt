package com.example.playlistapp.ui.views

import androidx.annotation.DrawableRes
import com.example.playlistapp.R

data class InsertArtistUIState(
    @DrawableRes val picture: Int = R.drawable.cantor_metal,
    val name: String = "",
)
