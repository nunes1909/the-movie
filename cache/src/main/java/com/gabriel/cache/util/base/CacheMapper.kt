package com.gabriel.cache.util.base

interface CacheMapper<Cache, Data> {
    fun mapToData(type: Cache): Data

    fun mapToCache(type: Data): Cache

    fun mapToData(entity: List<Cache?>): List<Data?> {
        return entity.map { if (it == null) null else mapToData(it) }
    }

    fun mapToCache(domain: List<Data?>): List<Cache?> {
        return domain.map { if (it == null) null else mapToCache(it) }
    }

    fun mapToDataNonNull(entity: List<Cache>): List<Data> {
        return entity.map { mapToData(it) }
    }

    fun mapToCacheNonNull(domain: List<Data>): List<Cache> {
        return domain.map { mapToCache(it)!! }
    }
}
