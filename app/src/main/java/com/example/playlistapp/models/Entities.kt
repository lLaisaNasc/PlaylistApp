package com.example.playlistapp.models

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.playlistapp.R

@Entity(tableName = "Song" )
data class Song(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
)

@Entity(tableName = "Artist")
data class Artist(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val picture: Int = R.drawable.cantor_metal,
    val name: String = "",
)

@Entity(tableName = "song_artist", primaryKeys = ["song_id", "artist_id"])
data class SongArtist(
    @ColumnInfo(name = "song_id") val songId: Int,
    @ColumnInfo(name = "artist_id") val artistId: Int,
)

data class SongWithArtists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            SongArtist::class,
            parentColumn = "song_id",
            entityColumn = "artist_id"
        )
    )

    val artists: List<Artist>
)

data class ArtistWithSongs(
    @Embedded val artist: Artist,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            SongArtist::class,
            parentColumn = "artist_id",
            entityColumn = "song_id"
        )
    )
    val songs: List<Song>

)
