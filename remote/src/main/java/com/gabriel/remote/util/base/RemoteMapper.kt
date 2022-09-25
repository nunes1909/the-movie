package com.gabriel.remote.util.base

interface RemoteMapper<Remote, Data> {
    fun mapToData(type: Remote): Data

    fun mapToRemote(type: Data): Remote

    fun mapToData(entity: List<Remote?>): List<Data?> {
        return entity.map { if (it == null) null else mapToData(it) }
    }

    fun mapToRemote(data: List<Data?>): List<Remote?> {
        return data.map { if (it == null) null else mapToRemote(it) }
    }

    fun mapToDataNonNull(remote: List<Remote>): List<Data> {
        return remote.map { mapToData(it) }
    }

    fun mapToRemoteNonNull(data: List<Data>): List<Remote> {
        return data.map { mapToRemote(it)!! }
    }
}
