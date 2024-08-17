package com.example.playlistapp.ui.views

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playlistapp.R
import com.example.playlistapp.models.ArtistWithSongs
import com.example.playlistapp.models.SongWithArtists
import com.example.playlistapp.viewmodels.PlaylistViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val viewModel: PlaylistViewModel = viewModel(factory = PlaylistViewModel.Factory)
    val navController = rememberNavController()
    val uiState by viewModel.appUIState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = uiState.title),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.navigate(navController = navController) }) {
                Image(
                    painter = painterResource(id = uiState.fabIcon),
                    contentDescription = stringResource(id = uiState.iconContentDescription)
                )
            }
        },
        modifier = modifier.background(Color.Transparent)
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.Playlist.name,
            modifier = modifier
                .padding(it)
                .background(Color.Transparent)
        ) {
            composable(route = AppScreens.Playlist.name) {
                Playlist()
            }

            composable(route = AppScreens.InsertSong.name) {
                InsertSong(viewModel = viewModel, navController = navController)
            }
        }
    }
}

//@Composable
//fun SongDetailsScreen(
//    modifier: Modifier = Modifier,
//    songWithArtists: SongWithArtists
//) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text(text = songWithArtists.song.title)
//        Spacer(modifier = Modifier.height(5.dp))
//        LazyColumn {
//            items(songWithArtists.artists){ artist ->
//                Card {
//                    Text(text = artist.name)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ArtistDetailsScreen(
//    modifier: Modifier = Modifier,
//    artistWithSongs: ArtistWithSongs,
//) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        //-- colocar junto a picture do artista e as musicas dele--
//        Text(text = artistWithSongs.artist.name)
//        Spacer(modifier = Modifier.height(5.dp))
//        LazyColumn {
//            items(artistWithSongs.songs){ song ->
//                Card {
//                    Text(text = song.title)
//                }
//            }
//        }
//    }
//}


enum class AppScreens {
    Playlist,
    InsertSong,
}