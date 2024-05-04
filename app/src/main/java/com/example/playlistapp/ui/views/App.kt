package com.example.playlistapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playlistapp.viewmodels.PlaylistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val viewModel: PlaylistViewModel = viewModel()
    val navController = rememberNavController()
    val uiState by viewModel.appUIState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = uiState.title))
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.navigate(navController = navController) }) {
                Image(
                    painter = painterResource(id = uiState.fabIcon),
                    contentDescription = stringResource(id = uiState.iconContentDescription)
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.Playlist.name,
            modifier = modifier.padding(it)
        ) {
            composable(route = AppScreens.Playlist.name) {
                Playlist(viewModel = viewModel, navController = navController)
            }

            composable(route = AppScreens.InsertSong.name) {
                InsertSong(viewModel = viewModel, navController = navController)
            }
        }
    }
}

enum class AppScreens {
    Playlist,
    InsertSong
}