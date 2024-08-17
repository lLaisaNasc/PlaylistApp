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
fun InsertArtist(viewModel: PlaylistViewModel, navController: NavController) {
    BackHandler {
        viewModel.navigateBack(navController = navController)
    }
    val uiState by viewModel.insertArtistUIState.collectAsState()
    InsertArtistForm(
        picture = uiState.picture,
        artistName = uiState.name,
        onUpdatePicture = viewModel::onPictureChange,
        onUpdateName = viewModel::onNameChange,
    )
}

@Composable
fun InsertArtistForm(
    @DrawableRes picture: Int,
    artistName: String,
    onUpdatePicture: (Int) -> Unit,
    onUpdateName: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val singerImageList = listOf(
        R.drawable.cantor_metal,
        R.drawable.cantor_pop,
        R.drawable.cantor_rock,
        R.drawable.cantor_sertanejo,
        R.drawable.cantor_samba,
        R.drawable.cantora_kpop,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier.fillMaxWidth()) {
            items(singerImageList) { singerImage ->
                Box(
                    modifier = modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .background(if (singerImage == picture) Color.LightGray else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = singerImage),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clickable {
                                onUpdatePicture(singerImage)
                            })
                }
            }
        }

        Spacer(modifier = modifier.height(8.dp))

        TextField(
            value = artistName,
            onValueChange = onUpdateName,
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
fun InsertArtistPreview() {
    InsertArtistForm(R.drawable.cantor_metal, "", {}, {})
}