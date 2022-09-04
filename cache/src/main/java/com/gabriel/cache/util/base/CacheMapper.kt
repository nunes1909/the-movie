package com.gabriel.cache.util.base

interface CacheMapper<Cache, Data> {
    fun mapToData(type: Cache): Data

    fun mapFromData(type: Data): Cache

    fun mapToData(entity: List<Cache?>): List<Data?> {
        return entity.map { if (it == null) null else mapToData(it) }
    }

    fun mapFromDomainNonNull(entity: List<Cache>): List<Data> {
        return entity.map { mapToData(it) }
    }

    fun mapFromData(domain: List<Data?>): List<Cache?> {
        return domain.map { if (it == null) null else mapFromData(it) }
    }

    fun mapToDomainNonNull(domain: List<Data>): List<Cache> {
        return domain.map { mapFromData(it)!! }
    }
}
