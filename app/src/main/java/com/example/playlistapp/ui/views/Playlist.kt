package com.example.playlistapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.navigation.compose.rememberNavController
import com.example.playlistapp.R
import com.example.playlistapp.models.Artist
import com.example.playlistapp.models.Song
import com.example.playlistapp.viewmodels.PlaylistViewModel
import kotlin.reflect.KFunction1

@Composable
fun Playlist(
    modifier: Modifier = Modifier
) {
    val viewModel: PlaylistViewModel = viewModel(factory = PlaylistViewModel.Factory)

    val songs by viewModel.songs.collectAsState()
    val artists by viewModel.artists.collectAsState()
    val songWithArtists by viewModel.songWithArtists.collectAsState()
    val artistWithSongs by viewModel.artistWithSongs.collectAsState()

    val navController = rememberNavController()

    ArtistSongListScreen(artists = artists, songs = songs)
}

@Composable
fun ArtistSongListScreen(
    artists: List<Artist>,
    songs: List<Song>
) {
    Column {
        Text(text = "Artists")
        LazyColumn {
            items(artists) { artist ->
                Text(text = artist.name)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Songs")
        LazyColumn {
            items(songs) { song ->
                Text(text = song.title)
            }
        }
    }
}
//    LazyColumn {
//        items(songs) { song ->
//            SongItem(
//                song = song,
//                onEditSong = viewModel::updateSong,
//                onDelete = viewModel::deleteSong,
//                modifier = modifier
//            )
//        }
//    }
//}

//@Composable
//fun SongItem(
//    song: Song,
//    onEditSong: KFunction1<Song, Unit>,
//    onDelete: (Song) -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(2.dp)
//            .clickable { onEditSong(song) }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//                .fillMaxWidth()
//        ) {
//            Column {
//                Text(
//                    text = song.title,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    color = Color.Black
//                )
//            }
//            Spacer(modifier = modifier.weight(1F))
//            Image(
//                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
//                contentDescription = "delete",
//                modifier = modifier
//                    .padding(end = 4.dp)
//                    .clickable { onDelete(song) }
//            )
//        }
//    }
//}

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