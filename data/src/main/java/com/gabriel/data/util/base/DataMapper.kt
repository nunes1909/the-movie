package com.gabriel.data.util.base

interface DataMapper<A, D> {
    fun mapToDomain(type: A): D

    fun mapFromDomain(type: D): A

    fun mapToDomain(entity: List<A?>): List<D?> {
        return entity.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapFromDomainNonNull(entity: List<A>): List<D> {
        return entity.map { mapToDomain(it) }
    }

    fun mapFromDomain(domain: List<D?>): List<A?> {
        return domain.map { if (it == null) null else mapFromDomain(it) }
    }

    fun mapToDomainNonNull(domain: List<D>): List<A> {
        return domain.map { mapFromDomain(it)!! }
    }
}
