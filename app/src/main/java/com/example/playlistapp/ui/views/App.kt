package com.example.playlistapp.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.playlistapp.viewmodels.PlaylistViewModel

@Composable
fun App(modifier: Modifier = Modifier) {

    val viewModel: PlaylistViewModel = viewModel()
    val navController = rememberNavController()
    val uiState by viewModel.appUIState.collectAsState()
}

enum class AppScreens{
    Playlist,
    InsertSong
}