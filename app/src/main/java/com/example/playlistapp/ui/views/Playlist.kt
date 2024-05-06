package com.example.playlistapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.playlistapp.R
import com.example.playlistapp.models.Song
import com.example.playlistapp.ui.theme.PlaylistAppTheme
import com.example.playlistapp.viewmodels.PlaylistViewModel

@Composable
fun Playlist(
    viewModel: PlaylistViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.playlistUIState.collectAsState()

    LazyColumn {
        items(uiState.playlist) { song ->
            SongItem(
                song = song,
                onEditSong = { viewModel.onEditSong(song = song, navController = navController) },
                onDelete = viewModel::deleteSong,
                modifier = modifier
            )
        }
    }
}

@Composable
fun SongItem(
    song: Song,
    onEditSong: () -> Unit,
    onDelete: (Song) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onEditSong() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = song.picture),
                contentDescription = null,
                modifier = modifier
                    .size(50.dp)
                    .padding(start = 4.dp, end = 4.dp)
            )
            Column {
                Text(
                    text = song.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = song.artist,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "delete",
                modifier = modifier
                    .padding(end = 4.dp)
                    .clickable { onDelete(song) }
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun SongItemPreview(
) {
    PlaylistAppTheme {
        SongItem(
            song = Song(
                R.drawable.estrela_do_rock,
                "Baby said",
                "Maneskin"
            ), {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaylistPreview(
) {
    PlaylistAppTheme {
        Playlist(viewModel())
    }
}
 */