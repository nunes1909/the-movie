package com.gabriel.cache.movie.favoritos.dao

import androidx.room.*
import com.gabriel.cache.movie.favoritos.model.FavoritoCache
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Dao
interface FavoritosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: FavoritoCache): Flow<ResourceState<Boolean>> {
        return flow {
            emit(ResourceState.Success(data = true))
        }
    }

    @Delete
    fun delete(entity: FavoritoCache): Flow<ResourceState<Boolean>> {
        return flow {
            emit(ResourceState.Success(data = true))
        }
    }

    @Query("SELECT * FROM MOVIES_FAVORITOS ORDER BY ID")
    fun getAllFav(): Flow<ResourceState<List<FavoritoCache>>>
}