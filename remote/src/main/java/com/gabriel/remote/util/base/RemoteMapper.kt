package com.gabriel.remote.util.base

interface RemoteMapper<Remote, Data> {
    fun mapToData(type: Remote): Data

    fun mapFromRemote(type: Data): Remote

    fun mapToData(entity: List<Remote?>): List<Data?> {
        return entity.map { if (it == null) null else mapToData(it) }
    }

    fun mapFromDomainNonNull(entity: List<Remote>): List<Data> {
        return entity.map { mapToData(it) }
    }

    fun mapFromRemote(domain: List<Data?>): List<Remote?> {
        return domain.map { if (it == null) null else mapFromRemote(it) }
    }

    fun mapToDomainNonNull(domain: List<Data>): List<Remote> {
        return domain.map { mapFromRemote(it)!! }
    }
}
