package com.example.playlistapp.ui.views

import androidx.annotation.DrawableRes
import com.example.playlistapp.R

data class InsertSongUIState(
    @DrawableRes val picture: Int = R.drawable.cantor_metal,
    val title: String = "",
)
