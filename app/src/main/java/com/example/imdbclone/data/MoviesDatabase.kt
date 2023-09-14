package com.example.imdbclone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imdbclone.data.models.MovieData

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDataabase(context: Context): MoviesDatabase {
            val temp = INSTANCE
            if(temp != null){
                return temp
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}