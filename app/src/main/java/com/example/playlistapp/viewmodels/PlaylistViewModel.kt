package com.example.playlistapp.viewmodels


import androidx.annotation.DrawableRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavController
import com.example.playlistapp.R
import com.example.playlistapp.SongApplication
import com.example.playlistapp.models.Artist
import com.example.playlistapp.models.ArtistWithSongs
import com.example.playlistapp.models.PlaylistDao
import com.example.playlistapp.models.PlaylistRoomDatabase
import com.example.playlistapp.models.Song
import com.example.playlistapp.models.SongArtist
import com.example.playlistapp.models.SongWithArtists
import com.example.playlistapp.ui.views.AppScreens
import com.example.playlistapp.ui.views.AppUIState
import com.example.playlistapp.ui.views.ArtistListUIState
import com.example.playlistapp.ui.views.InsertArtistUIState
import com.example.playlistapp.ui.views.InsertSongUIState
import com.example.playlistapp.ui.views.SongListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val playlistDao: PlaylistDao,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

//    init {
//    createPlaylist()
//    }

    private val _appUIState: MutableStateFlow<AppUIState> =
        MutableStateFlow(AppUIState())

    val appUIState: StateFlow<AppUIState> =
        _appUIState.asStateFlow()

    private val _insertArtistUIState: MutableStateFlow<InsertArtistUIState> =
        MutableStateFlow(InsertArtistUIState())

    val insertArtistUIState: StateFlow<InsertArtistUIState> =
        _insertArtistUIState.asStateFlow()

    private val _insertSongUIState: MutableStateFlow<InsertSongUIState> =
        MutableStateFlow(InsertSongUIState())

    val insertSongUIState: StateFlow<InsertSongUIState> =
        _insertSongUIState.asStateFlow()

    val songs: StateFlow<List<Song>> =
        playlistDao.getAllSongs()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )

    val artists: StateFlow<List<Artist>> =
        playlistDao.getAllArtists()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )

    private var songId = MutableStateFlow(0)
    private var artistId = MutableStateFlow(0)

    val songWithArtists: StateFlow<SongWithArtists> =
        songId.flatMapLatest { id ->
            playlistDao.getSongWithArtist(id)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = SongWithArtists(Song(0, ""), listOf())
            )

    val artistWithSongs: StateFlow<ArtistWithSongs> =
        artistId.flatMapLatest { id ->
            playlistDao.getArtistWithSongs(id)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ArtistWithSongs(Artist(0, 0, ""), listOf())
            )

    // -- song --
    fun insertSong(song: Song) {
        viewModelScope.launch {
            playlistDao.insertSong(song)
        }
    }

    fun updateSong(song: Song) {
        viewModelScope.launch {
            playlistDao.updateSong(song)
        }
    }

    fun deleteSong(song: Song) {
        viewModelScope.launch {
            playlistDao.deleteSong(song)
        }
    }

    fun selectSong(song: Song) {
        songId.value = song.id
    }

    // -- artist --
    fun insertArtist(artist: Artist) {
        viewModelScope.launch {
            playlistDao.insertArtist(artist)
        }
    }

    fun updateArtist(artist: Artist) {
        viewModelScope.launch {
            playlistDao.updateArtist(artist)
        }
    }

    fun deleteArtist(artist: Artist) {
        viewModelScope.launch {
            playlistDao.deleteArtist(artist)
        }
    }

    fun selectArtist(artist: Artist) {
        artistId.value = artist.id
    }

    // -- song_artist --
    fun insertSongArtist(songArtist: SongArtist) {
        viewModelScope.launch {
            playlistDao.insertSongArtist(songArtist)
        }
    }

    fun deleteSongArtist(songArtist: SongArtist) {
        viewModelScope.launch {
            playlistDao.deleteSongArtist(songArtist)
        }
    }

    fun createPlaylist() {

        viewModelScope.launch {

            val songs = listOf(
                Song(title = "Stairway to Heaven"),
                Song(title = "All My Love"),
                Song(title = "Smells Like Teen Spirit"),
                Song(title = "Come as You Are"),
                Song(title = "Shape of You"),
                Song(title = "Thinking Out Loud"),
                Song(title = "Rolling in the Deep"),
                Song(title = "Skyfall"),
                Song(title = "Do I Wanna Know?"),
                Song(title = "505"),
                Song(title = "R U Mine?"),
                Song(title = "Bad Romance"),
                Song(title = "Paparazzi"),
                Song(title = "Yellow"),
                Song(title = "Adventure of Lifetime"),
                Song(title = "Clocks"),
            )

            val artists = listOf(
                Artist(name = "Led Zeppelin", picture = R.drawable.cantor_metal),
                Artist(name = "Nirvana", picture = R.drawable.cantor_metal),
                Artist(name = "Ed Sheeran", picture = R.drawable.cantor_pop),
                Artist(name = "Adele", picture = R.drawable.cantora_kpop),
                Artist(name = "Arctic Monkeys", picture = R.drawable.cantor_rock),
                Artist(name = "Lady Gaga", picture = R.drawable.cantora_kpop),
                Artist(name = "Coldplay", picture = R.drawable.cantor_pop),
            )

            val songArtistRelations = listOf(

                // -- Led Zeppelin --
                SongArtist(songId = 1, artistId = 1),
                SongArtist(songId = 2, artistId = 1),

                // -- Nirvana --
                SongArtist(songId = 3, artistId = 2),
                SongArtist(songId = 4, artistId = 2),

                // -- Ed Sheeran --
                SongArtist(songId = 5, artistId = 3),
                SongArtist(songId = 6, artistId = 3),

                // -- Adele --
                SongArtist(songId = 7, artistId = 4),
                SongArtist(songId = 8, artistId = 4),

                // -- Arctic Monkeys --
                SongArtist(songId = 9, artistId = 5),
                SongArtist(songId = 10, artistId = 5),
                SongArtist(songId = 11, artistId = 5),

                // -- Lady Gaga --
                SongArtist(songId = 12, artistId = 6),
                SongArtist(songId = 13, artistId = 6),

                // -- Arctic Monkeys --
                SongArtist(songId = 14, artistId = 7),
                SongArtist(songId = 15, artistId = 7),
                SongArtist(songId = 16, artistId = 7),
            )


            playlistDao.insertAllArtists(artists)
            playlistDao.insertAllSongs(songs)
            playlistDao.insertAllRelation(songArtistRelations)
        }
    }

    private var songToEdit: Song = Song()
    private var editSong: Boolean = false

    private var artistToEdit: Artist = Artist()
    private var editArtist: Boolean = false

//    fun navigateArtist(navController: NavController) {
//
//        //--- verifica se a tela atual é a de read ---
//        if (_appUIState.value.title == R.string.playlist) {
//            _appUIState.update { currentState ->
//                currentState.copy(
//                    title = R.string.insert_a_new_artist,
//                    fabIcon = R.drawable.baseline_audiotrack_24,
//                    iconContentDescription = R.string.confirm
//                )
//            }
//            navController.navigate(AppScreens.InsertSong.name)
//        } else {
//            //--- verifica tela update ---
//            val playlist: MutableList<Song> =
//                _playlistUIState.value.playlist.toMutableList()
//            if (editSong) {
//                val position = playlist.indexOf(songToEdit)
//                playlist.removeAt(position)
//                playlist.add(
//                    position, Song(
//                        _insertFormUIState.value.picture,
//                        _insertFormUIState.value.title,
//                        _insertFormUIState.value.artist
//                    )
//                )
//
//                songToEdit = Song()
//                editSong = false
//
//            } else {
//                //--- tela create ---
//                playlist.add(
//                    Song(
//                        picture = _insertFormUIState.value.picture,
//                        title = _insertFormUIState.value.title,
//                        artist = _insertFormUIState.value.artist
//                    )
//                )
//            }
//
//            // --- mantém a visualização da playlist atualizada ---
//            _playlistUIState.update { currentState ->
//                currentState.copy(
//                    playlist = playlist.toList()
//                )
//            }
//
//            // --- volta ao padrão ---
//            _insertFormUIState.update {
//                InsertFormUIState()
//            }
//
//            _appUIState.update {
//                AppUIState()
//            }
//
//            // --- navega de volta a tela inicial ---
//            navController.navigate(AppScreens.Playlist.name) {
//                popUpTo(AppScreens.Playlist.name) {
//                    inclusive = true
//                }
//            }
//        }
//    }

    fun navigateArtist(navController: NavController) {
        when (appUIState.value.title) {
            R.string.artists -> navigateToInsertEditArtist(navController)
            R.string.insert_artist, R.string.update_artist -> navigateToArtistList(
                navController
            )

            else -> navigateToArtistList(navController)
        }
    }

    fun editArtist(artist: Artist) {
        editArtist = true
        artistToEdit = artist
    }

    fun navigateArtistDetails(navController: NavController) {
        _appUIState.update { currentState ->
            currentState.copy(
                title = R.string.artist_details,
                fabIcon = R.drawable.baseline_audiotrack_24,
                iconContentDescription = R.string.confirm
            )
        }
        navController.navigate(AppScreens.ArtistDetailsScreen.name)
    }

    private fun navigateToInsertEditArtist(navController: NavController) {
        if (editArtist) {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.insert_artist,
                    fabIcon = R.drawable.baseline_check_24,
                )
            }
            _insertArtistUIState.update { currentState ->
                currentState.copy(
                    name = artistToEdit.name,
                )
            }
        } else {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.insert_artist,
                    fabIcon = R.drawable.baseline_check_24,
                )
            }
        }
        navController.navigate(AppScreens.InsertArtist.name)
    }

    private fun navigateToArtistList(navController: NavController) {
        if (_appUIState.value.title == R.string.playlist) {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.artists,
                    fabIcon = R.drawable.baseline_audiotrack_24,
                    iconContentDescription = R.string.confirm
                )
            }
            navController.navigate(AppScreens.ArtistListScreen.name)
        } else {
            if (editArtist) {
                val artist = artistToEdit.copy(
                    name = insertArtistUIState.value.name,
                    picture = insertArtistUIState.value.picture,
                )
                viewModelScope.launch {
                    updateArtist(artist)
                }
                editArtist = false
                artistToEdit = Artist()
            } else {
                val artist = artistToEdit.copy(
                    name = insertArtistUIState.value.name,
                    picture = insertArtistUIState.value.picture,
                )
                viewModelScope.launch {
                    insertArtist(artist)
                }
            }
            _insertArtistUIState.update { currentState ->
                currentState.copy(
                    name = "",
                    picture = R.drawable.cantor_pop
                )
            }
            _appUIState.update { currentState ->
                currentState.copy(title = R.string.artists, fabIcon = R.drawable.baseline_add_24)
            }
            navController.navigate(AppScreens.ArtistListScreen.name) {
                popUpTo(AppScreens.ArtistListScreen.name) {
                    inclusive = true
                }
            }
        }

    }

    fun editSong(song: Song) {
        editSong = true
        songToEdit = song
    }

    fun navigateSong(navController: NavController) {
        when (appUIState.value.title) {
            R.string.songs -> navigateToInsertEditSong(navController)
            R.string.insert_song, R.string.update_song -> navigateToSongList(navController)
            else -> navigateToSongList(navController)
        }
    }

    private fun navigateToInsertEditSong(navController: NavController) {
        if (editSong) {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.update_song,
                    fabIcon = R.drawable.baseline_check_24,
                )
            }
            _insertSongUIState.update { currentState ->
                currentState.copy(
                    title = songToEdit.title,
                )
            }
        } else {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.insert_song,
                    fabIcon = R.drawable.baseline_check_24,
                )
            }
        }
        navController.navigate(AppScreens.InsertSong.name)
    }

    private fun navigateToSongList(navController: NavController) {
        if (_appUIState.value.title == R.string.playlist) {
            _appUIState.update { currentState ->
                currentState.copy(
                    title = R.string.songs,
                    fabIcon = R.drawable.baseline_audiotrack_24,
                    iconContentDescription = R.string.confirm
                )
            }
            navController.navigate(AppScreens.SongListScreen.name)
        } else {
            if (editSong) {
                val song = songToEdit.copy(
                    title = insertSongUIState.value.title,
                )
                viewModelScope.launch {
                    updateSong(song)
                }
                editSong = false
                songToEdit = Song()
            } else {
                val song = songToEdit.copy(
                    title = insertSongUIState.value.title,
                )
                viewModelScope.launch {
                    insertSong(song)
                }
            }
            _insertSongUIState.update { currentState ->
                currentState.copy(
                    title = "",
                )
            }
            _appUIState.update { currentState ->
                currentState.copy(title = R.string.song_list, fabIcon = R.drawable.baseline_add_24)
            }

            navController.navigate(AppScreens.SongListScreen.name) {
                popUpTo(AppScreens.SongListScreen.name) {
                    inclusive = true
                }
            }
        }

    }

    fun navigateSongDetails(navController: NavController) {
        _appUIState.update { currentState ->
            currentState.copy(
                title = R.string.song_details,
                fabIcon = R.drawable.baseline_audiotrack_24,
                iconContentDescription = R.string.confirm
            )
        }
        navController.navigate(AppScreens.SongDetailsScreen.name)
    }

    fun navigateBack(navController: NavController) {
        editArtist = false
        artistToEdit = Artist()
        _insertArtistUIState.update { currentState ->
            currentState.copy(
                name = "",
                picture = R.drawable.cantor_pop,
            )
        }
        _appUIState.update { currentState ->
            currentState.copy(title = R.string.playlist, fabIcon = R.drawable.baseline_add_24)
        }
        navController.popBackStack()
    }

    fun onPictureChange(@DrawableRes picture: Int) {
        _insertArtistUIState.update { currentState ->
            currentState.copy(
                picture = picture
            )
        }
    }

    fun onNameChange(name: String) {
        _insertArtistUIState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }

    fun onTitleChange(title: String) {
        _insertSongUIState.update { currentState ->
            currentState.copy(
                title = title
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras,
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val savedStateHandle = extras.createSavedStateHandle()
                return PlaylistViewModel(
                    (application as SongApplication).database.playlistDao(),
                    savedStateHandle,
                ) as T
            }
        }
    }
}

