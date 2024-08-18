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
import androidx.navigation.compose.rememberNavController
import com.example.playlistapp.R
import com.example.playlistapp.models.Artist
import com.example.playlistapp.viewmodels.PlaylistViewModel
import kotlin.reflect.KFunction1

@Composable
fun ArtistListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val viewModel: PlaylistViewModel = viewModel(factory = PlaylistViewModel.Factory)

    val artists by viewModel.artists.collectAsState()

    ArtistList(
        modifier = modifier,
        viewModel = viewModel,
        navController = navController,
        artists = artists,
        onArtistSelection = viewModel::selectArtist,
        showDetails = viewModel::navigateArtist,
    )
}

@Composable
fun ArtistList(
    modifier: Modifier = Modifier,
    viewModel: PlaylistViewModel,
    navController: NavController,
    artists: List<Artist>,
    onArtistSelection: (Artist) -> Unit,
    showDetails: KFunction1<NavController, Unit>,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // artists title
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "ARTISTS".uppercase(),
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.Black
                )
            }
        }

        // artists list
        items(artists) { artist ->
            ArtistItem(
                modifier = modifier,
                navController = navController,
                artist = artist,
                onArtistSelection = onArtistSelection,
                onArtistClick = showDetails,
                onDelete = viewModel::deleteArtist,
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun ArtistItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    artist: Artist,
    onArtistSelection: (Artist) -> Unit,
    onArtistClick: KFunction1<NavController, Unit>,
    onDelete: (Artist) -> Unit,
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(2.dp)
        .clickable {
            onArtistSelection(artist)
            onArtistClick(navController)

        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            Column {
                Text(
                    text = artist.name,
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
                    .clickable { onDelete(artist) })
        }
    }
}