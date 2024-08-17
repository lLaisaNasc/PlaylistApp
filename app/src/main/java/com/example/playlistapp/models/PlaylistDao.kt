package com.example.playlistapp.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    // -- Song Artist --
    @Query("SELECT * FROM Song WHERE id = :id")
    fun getSongWithArtist(id: Int): Flow<SongWithArtists>

    @Query("SELECT * FROM Artist WHERE id = :id")
    fun getArtistWithSongs(id: Int): Flow<ArtistWithSongs>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllRelation(relations: List<SongArtist>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSongArtist(songArtist: SongArtist)

    @Delete
    suspend fun deleteSongArtist(songArtist: SongArtist)

    // -- Song --
    @Query("SELECT * FROM Song")
    fun getAllSongs(): Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllSongs(songs: List<Song>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSong(song: Song)

    @Update()
    suspend fun updateSong(song: Song)

    @Delete()
    suspend fun deleteSong(song: Song)

    // -- Artist --
    @Query("SELECT * FROM Artist")
    fun getAllArtists(): Flow<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllArtists(artists: List<Artist>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArtist(artist: Artist)

    @Update()
    suspend fun updateArtist(artist: Artist)

    @Delete()
    suspend fun deleteArtist(artist: Artist)


}