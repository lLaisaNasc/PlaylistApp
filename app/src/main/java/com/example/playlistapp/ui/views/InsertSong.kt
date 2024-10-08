package com.example.playlistapp.ui.views

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.playlistapp.R
import com.example.playlistapp.viewmodels.PlaylistViewModel

@Composable
fun InsertSong(viewModel: PlaylistViewModel, navController: NavController) {
    BackHandler {
        viewModel.navigateBack(navController = navController)
    }
    val uiState by viewModel.insertSongUIState.collectAsState()
    InsertSongForm(
        songName = uiState.title,
        onUpdateSongName = viewModel::onTitleChange,
    )
}

@Composable
fun InsertSongForm(
    songName: String,
    onUpdateSongName: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(modifier = modifier.height(8.dp))

        TextField(
            value = songName,
            onValueChange = onUpdateSongName,
            label = { Text(text = "Song name") },
            singleLine = false,
            minLines = 1,
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = modifier.height(8.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun InsertSongPreview() {
    InsertSongForm("", {})
}