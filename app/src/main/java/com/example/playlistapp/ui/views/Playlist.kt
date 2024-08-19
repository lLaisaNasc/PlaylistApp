package com.example.playlistapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.playlistapp.models.Artist
import com.example.playlistapp.models.Song
import com.example.playlistapp.viewmodels.PlaylistViewModel
import kotlin.reflect.KFunction1

@Composable
fun Playlist(
    modifier: Modifier = Modifier,
    viewModel: PlaylistViewModel,
    navController: NavController
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)
                .clickable {
                    viewModel.navigateArtist(navController)
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                Text(
                    text = "ARTISTAS",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)
                .clickable {
                    viewModel.navigateSong(navController)
                }
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                Text(
                    text = "MÚSICAS",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White
                )
            }
        }
    }
}


//@Composable
//fun Playlist(
//    modifier: Modifier = Modifier,
//    navController: NavController
//) {
//    val viewModel: PlaylistViewModel = viewModel(factory = PlaylistViewModel.Factory)
//
//    val songs by viewModel.songs.collectAsState()
//    val artists by viewModel.artists.collectAsState()
//
//    ArtistSongListScreen(
//        modifier = modifier,
//        viewModel = viewModel,
//        navController = navController,
//        artists = artists,
//        songs = songs,
//        onArtistClick = viewModel::navigateArtistList,
//        onArtistSelection = viewModel::selectArtist,
//        onSongSelection = viewModel::selectSong
//    )
//}
//
//@Composable
//fun ArtistSongListScreen(
//    modifier: Modifier = Modifier,
//    viewModel: PlaylistViewModel,
//    navController: NavController,
//    artists: List<Artist>,
//    songs: List<Song>,
//    onArtistClick: KFunction1<NavController, Unit>,
//    onArtistSelection: (Artist) -> Unit,
//    onSongSelection: (Song) -> Unit,
//) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        // artists title
//        item {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                Text(
//                    text = "ARTISTS".uppercase(),
//                    modifier = Modifier.align(Alignment.Center),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 25.sp,
//                    color = Color.Red
//                )
//            }
//        }
//
//        // artists list
//        items(artists) { artist ->
//            ArtistItem(
//                modifier = modifier,
//                navController = navController,
//                artist = artist,
//                onArtistClick = onArtistClick,
//                onArtistSelection = onArtistSelection,
//                onEditArtist = viewModel::updateArtist,
//                onDelete = viewModel::deleteArtist,
//            )
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        // songs title
//        item {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                Text(
//                    text = "SONGS".uppercase(),
//                    modifier = Modifier.align(Alignment.Center),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 25.sp,
//                    color = Color.Red
//                )
//            }
//        }
//
//        // songs list
//        items(songs) { song ->
//            SongItem(
//                modifier = modifier,
//                navController = navController,
//                song = song,
//                onSongSelection = onSongSelection,
//                onEditSong = viewModel::updateSong,
//                onDelete = viewModel::deleteSong,
//            )
//        }
//    }
//}
//
//@Composable
//fun ArtistItem(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    artist: Artist,
//    onArtistClick: KFunction1<NavController, Unit>,
//    onArtistSelection: (Artist) -> Unit,
//    onEditArtist: KFunction1<Artist, Unit>,
//    onDelete: (Artist) -> Unit,
//) {
//    Card(modifier = modifier
//        .fillMaxWidth()
//        .padding(2.dp)
//        .clickable {
//            onArtistSelection(artist)
//            onArtistClick(navController)
//        }) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp, horizontal = 4.dp)
//        ) {
//            Column {
//                Text(
//                    text = artist.name,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    color = Color.Black
//                )
//            }
//            Spacer(modifier = modifier.weight(1F))
//            Image(painter = painterResource(id = R.drawable.baseline_delete_outline_24),
//                contentDescription = "delete",
//                modifier = modifier
//                    .padding(end = 4.dp)
//                    .clickable { onDelete(artist) })
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