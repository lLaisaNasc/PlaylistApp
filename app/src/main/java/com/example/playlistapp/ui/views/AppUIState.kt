package com.example.playlistapp.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.playlistapp.R

data class AppUIState(
    @StringRes
    val title: Int = R.string.playlist,

    @DrawableRes
    val fabIcon: Int = R.drawable.baseline_audiotrack_24,

    @StringRes
    val iconContentDescription: Int = R.string.insert_a_new_song,
)
