package com.example.playlistapp.ui.views

import androidx.annotation.DrawableRes
import com.example.playlistapp.R

data class InsertFormUIState(
    @DrawableRes val picture: Int = R.drawable.estrela_do_rock,
    val title: String = "",
    val artist: String = "",
)
