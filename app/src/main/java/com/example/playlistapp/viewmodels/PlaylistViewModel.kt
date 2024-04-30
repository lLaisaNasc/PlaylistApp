package com.example.playlistapp.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.playlistapp.R
import com.example.playlistapp.data.createPlaylist
import com.example.playlistapp.models.Song
import com.example.playlistapp.ui.views.AppScreens
import com.example.playlistapp.ui.views.AppUIState
import com.example.playlistapp.ui.views.InsertFormUIState
import com.example.playlistapp.ui.views.PlaylistUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlaylistViewModel : ViewModel() {

    private val _playlistUIState: MutableStateFlow<PlaylistUIState> =
        MutableStateFlow(PlaylistUIState(createPlaylist()))

    val playlistUIState: StateFlow<PlaylistUIState> =
        _playlistUIState.asStateFlow()

    private val _insertFormUIState: MutableStateFlow<InsertFormUIState> =
        MutableStateFlow(InsertFormUIState())

    val insertFormUIState: StateFlow<InsertFormUIState> =
        _insertFormUIState.asStateFlow()

    private val _appUIState: MutableStateFlow<AppUIState> =
        MutableStateFlow(AppUIState())

    val appUIState: StateFlow<AppUIState> =
        _appUIState.asStateFlow()

    private var songToEdit: Song = Song()
    private var editSong: Boolean = false

    fun navigate(navController: NavController) {

        //--- verifica se a tela atual é a de read ---
        if (_appUIState.value.title == R.string.playlist) {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.insert_a_new_song,
                    fabIcon = R.drawable.baseline_audiotrack_24,
                    iconContentDescription = R.string.confirm
                )
            }
            navController.navigate(AppScreens.InsertSong.name)
        } else {
            //--- verifica tela update ---
            val playlist: MutableList<Song> =
                _playlistUIState.value.playlist.toMutableList()
            if (editSong) {
                val position = playlist.indexOf(songToEdit)
                playlist.removeAt(position)
                playlist.add(
                    position, Song(
                        _insertFormUIState.value.picture,
                        _insertFormUIState.value.title,
                        _insertFormUIState.value.artist
                    )
                )

                songToEdit = Song()
                editSong = false

            } else {
                //--- tela create ---
                playlist.add(
                    Song(
                        picture = _insertFormUIState.value.picture,
                        title = _insertFormUIState.value.title,
                        artist = _insertFormUIState.value.artist
                    )
                )
            }

            // --- mantém a visualização da playlist atualizada ---
            _playlistUIState.update { currentState ->
                currentState.copy(
                    playlist = playlist.toList()
                )
            }

            // --- volta ao padrão ---
            _insertFormUIState.update {
                InsertFormUIState()
            }

            _appUIState.update {
                AppUIState()
            }

            // --- navega de volta a tela inicial ---
            navController.navigate(AppScreens.Playlist.name) {
                popUpTo(AppScreens.Playlist.name) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateBack(navController: NavController) {
        _appUIState.update {
            AppUIState()
        }
        navController.popBackStack()
    }

    fun deleteSong(song: Song) {
        // -- encontra a lista de músicas ---
        val playlist: MutableList<Song> =
            _playlistUIState.value.playlist.toMutableList()

        playlist.remove(song)

        // --- atualiza a vizualização ---
        _playlistUIState.value = _playlistUIState.value.copy(
            playlist = playlist.toList()
        )
    }

    fun onEditSong(song: Song, navController: NavController) {
        editSong = true
        songToEdit = song

        // -- renderiza os dados da música a ser editada ---
        _insertFormUIState.update { currentState ->
            currentState.copy(
                picture = songToEdit.picture,
                title = songToEdit.title,
                artist = songToEdit.artist
            )
        }

        // -- atualiza a tela para edição ---
        _appUIState.update { currentState ->
            currentState.copy(
                title = R.string.edit_song,
                fabIcon = R.drawable.baseline_check_circle_24,
                iconContentDescription = R.string.confirm
            )
        }

        navController.navigate(route = AppScreens.InsertSong.name)
    }

    fun onPictureChange(@DrawableRes picture: Int) {
        _insertFormUIState.update { currentState ->
            currentState.copy(
                picture = picture
            )
        }
    }

    fun onTitleChange(title: String) {
        _insertFormUIState.update { currentState ->
            currentState.copy(
                title = title
            )
        }
    }

    fun onArtistChange(artist: String) {
        _insertFormUIState.update { currentState ->
            currentState.copy(
                artist = artist
            )
        }
    }
}

