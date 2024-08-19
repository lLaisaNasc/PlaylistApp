package com.example.playlistapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.playlistapp.R
import com.example.playlistapp.models.Song
import com.example.playlistapp.viewmodels.PlaylistViewModel
import kotlin.reflect.KFunction1

@Composable
fun SongListScreen(
    modifier: Modifier = Modifier,
    viewModel: PlaylistViewModel,
    navController: NavController,
) {

    val songs by viewModel.songs.collectAsState()

    SongList(
        modifier = modifier,
        viewModel = viewModel,
        navController = navController,
        songs = songs,
        onSongSelection = viewModel::selectSong,
        showDetails = viewModel::navigateSong,
    )
}

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    viewModel: PlaylistViewModel,
    navController: NavController,
    songs: List<Song>,
    onSongSelection: (Song) -> Unit,
    showDetails: KFunction1<NavController, Unit>,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // songs list
        items(songs) { song ->
            SongItem(
                modifier = modifier,
                navController = navController,
                song = song,
                onSongSelection = onSongSelection,
                onSongClick = showDetails,
                onDelete = viewModel::deleteSong
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun SongItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    song: Song,
    onSongSelection: (Song) -> Unit,
    onSongClick: KFunction1<NavController, Unit>,
    onDelete: KFunction1<Song, Unit>,
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(2.dp)
        .clickable {
            onSongSelection(song)
            onSongClick(navController)
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            Column {
                Text(
                    text = song.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Image(painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "delete",
                modifier = modifier
                    .padding(end = 4.dp)
                    .clickable { onDelete(song) })
        }
    }
}