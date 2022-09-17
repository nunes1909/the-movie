package com.gabriel.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.model.FavoritoCache
import com.gabriel.cache.util.constants.CacheConstants.DB_NAME

@Database(
    entities = [FavoritoCache::class],
    version = 1,
    exportSchema = true
)
abstract class TheMovieDataBase : RoomDatabase() {
    abstract fun getFavoritosDao(): FavoritosDao
    companion object {
        fun getInstance(context: Context): TheMovieDataBase {
            return Room.databaseBuilder(
                context,
                TheMovieDataBase::class.java,
                DB_NAME
            ).build()
        }
    }
}
