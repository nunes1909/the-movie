package com.gabriel.cache.movie.favoritos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabriel.cache.util.constants.CacheConstants.COLUMN_BANNER
import com.gabriel.cache.util.constants.CacheConstants.COLUMN_CARTAZ
import com.gabriel.cache.util.constants.CacheConstants.COLUMN_DESC
import com.gabriel.cache.util.constants.CacheConstants.COLUMN_ID
import com.gabriel.cache.util.constants.CacheConstants.COLUMN_ID_API
import com.gabriel.cache.util.constants.CacheConstants.COLUMN_TITULO
import com.gabriel.cache.util.constants.CacheConstants.TABLE_FAV

@Entity(tableName = TABLE_FAV)
data class FavoritoCache(
    @ColumnInfo(name = COLUMN_ID)
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    @ColumnInfo(name = COLUMN_TITULO)
    val title: String,
    @ColumnInfo(name = COLUMN_DESC)
    val description: String? = null,
    @ColumnInfo(name = COLUMN_CARTAZ)
    val cartaz: String? = null,
    @ColumnInfo(name = COLUMN_BANNER)
    val banner: String? = null,
    @ColumnInfo(name = COLUMN_ID_API)
    val idApi: Int
)
