package com.gabriel.remote.util.base

interface RemoteMapper<Remote, Data> {
    fun mapToData(type: Remote): Data

    fun mapToRemote(type: Data): Remote

    fun mapToData(entity: List<Remote?>): List<Data?> {
        return entity.map { if (it == null) null else mapToData(it) }
    }

    fun mapToRemote(domain: List<Data?>): List<Remote?> {
        return domain.map { if (it == null) null else mapToRemote(it) }
    }

    fun mapToDataNonNull(entity: List<Remote>): List<Data> {
        return entity.map { mapToData(it) }
    }

    fun mapToRemoteNonNull(domain: List<Data>): List<Remote> {
        return domain.map { mapToRemote(it)!! }
    }
}
