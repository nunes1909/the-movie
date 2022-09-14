package com.gabriel.data.util.base

interface DataMapper<Data, Domain> {
    fun mapToDomain(type: Data): Domain

    fun mapFromDomain(type: Domain): Data

    fun mapToDomain(entity: List<Data?>): List<Domain?> {
        return entity.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapFromDomainNonNull(entityNonNull: List<Data>): List<Domain> {
        return entityNonNull.map { mapToDomain(it) }
    }

    fun mapFromDomain(domain: List<Domain?>): List<Data?> {
        return domain.map { if (it == null) null else mapFromDomain(it) }
    }

    fun mapToDomainNonNull(domainNonNull: List<Domain>): List<Data> {
        return domainNonNull.map { mapFromDomain(it)!! }
    }
}
