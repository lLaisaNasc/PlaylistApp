package com.example.playlistapp.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Song::class, Artist::class, SongArtist::class], version = 1, exportSchema = false)
abstract class PlaylistRoomDatabase : RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao

    // -- singleton --
    companion object {

        @Volatile
        private var instance: PlaylistRoomDatabase? = null

        fun getDatabase(context: Context): PlaylistRoomDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context.applicationContext,
                    PlaylistRoomDatabase::class.java,
                    "playlist_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}