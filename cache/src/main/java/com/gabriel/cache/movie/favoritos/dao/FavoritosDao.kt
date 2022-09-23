package com.gabriel.cache.movie.favoritos.dao

import androidx.room.*
import com.gabriel.cache.movie.favoritos.model.MovieCache
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Dao
interface FavoritosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: MovieCache): Long

    @Delete
    fun delete(entity: MovieCache)

    @Query("SELECT * FROM MOVIES_FAVORITOS ORDER BY ID")
    fun getAllFav(): Flow<List<MovieCache>>

    @Query("SELECT * FROM MOVIES_FAVORITOS WHERE TITULO LIKE :query")
    fun getQueryFav(query: String): Flow<List<MovieCache>>

    @Query("SELECT EXISTS (SELECT ID FROM MOVIES_FAVORITOS WHERE id = :id)")
    fun verifyExistsMovie(id: Int): Flow<Boolean>
}
