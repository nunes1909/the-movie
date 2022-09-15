package com.gabriel.data.util.base

interface DataMapper<Data, Domain> {
    fun mapToDomain(type: Data): Domain

    fun mapToData(type: Domain): Data

    fun mapToDomain(entity: List<Data?>): List<Domain?> {
        return entity.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapToData(domain: List<Domain?>): List<Data?> {
        return domain.map { if (it == null) null else mapToData(it) }
    }

    fun mapToDomainNonNull(entityNonNull: List<Data>): List<Domain> {
        return entityNonNull.map { mapToDomain(it) }
    }

    fun mapToDataNonNull(domainNonNull: List<Domain>): List<Data> {
        return domainNonNull.map { mapToData(it)!! }
    }
}
