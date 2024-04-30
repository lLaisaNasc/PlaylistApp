package com.example.playlistapp.models

import androidx.annotation.DrawableRes
import com.example.playlistapp.R

data class Song(
    @DrawableRes val picture: Int = R.drawable.estrela_do_rock,
    val title: String = "",
    val artist: String = "",
)
