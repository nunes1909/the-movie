package com.gabriel.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.model.MovieCache
import com.gabriel.cache.util.constants.CacheConstants.DB_NAME

@Database(
    entities = [MovieCache::class],
    version = 1,
    exportSchema = true
)
abstract class TheMovieDataBase : RoomDatabase() {
    abstract fun getFavoritosDao(): FavoritosDao
    companion object {
        @Volatile
        private var db: TheMovieDataBase? = null

        fun getInstance(context: Context): TheMovieDataBase {
            return db ?: Room.databaseBuilder(
                context,
                TheMovieDataBase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build().also {
                db = it
            }
        }
    }
}
