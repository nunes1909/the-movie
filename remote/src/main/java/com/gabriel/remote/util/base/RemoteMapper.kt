package com.gabriel.remote.util.base

interface RemoteMapper<Remote, Data> {
    fun mapToData(type: Remote): Data

    fun mapFromData(type: Data): Remote

    fun mapToData(entity: List<Remote?>): List<Data?> {
        return entity.map { if (it == null) null else mapToData(it) }
    }

    fun mapFromDataNonNull(entity: List<Remote>): List<Data> {
        return entity.map { mapToData(it) }
    }

    fun mapFromData(domain: List<Data?>): List<Remote?> {
        return domain.map { if (it == null) null else mapFromData(it) }
    }

    fun mapToDataNonNull(domain: List<Data>): List<Remote> {
        return domain.map { mapFromData(it)!! }
    }
}
