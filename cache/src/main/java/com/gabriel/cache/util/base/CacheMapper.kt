package com.gabriel.cache.util.base

interface CacheMapper<Cache, Data> {
    fun mapToData(type: Cache): Data

    fun mapToCache(type: Data): Cache

    fun mapToData(cache: List<Cache?>): List<Data?> {
        return cache.map { if (it == null) null else mapToData(it) }
    }

    fun mapToCache(data: List<Data?>): List<Cache?> {
        return data.map { if (it == null) null else mapToCache(it) }
    }

    fun mapToDataNonNull(data: List<Cache>): List<Data> {
        return data.map { mapToData(it) }
    }

    fun mapToCacheNonNull(data: List<Data>): List<Cache> {
        return data.map { mapToCache(it)!! }
    }
}
