package com.gabriel.themovie.util.base

interface ViewMapper<View, Domain> {
    fun mapToDomain(type: View): Domain

    fun mapFromDomain(type: Domain): View

    fun mapToDomain(entity: List<View?>): List<Domain?> {
        return entity.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapFromDomainNonNull(entity: List<View>): List<Domain> {
        return entity.map { mapToDomain(it) }
    }

    fun mapFromDomain(domain: List<Domain?>): List<View?> {
        return domain.map { if (it == null) null else mapFromDomain(it) }
    }

    fun mapToDomainNonNull(domain: List<Domain>): List<View> {
        return domain.map { mapFromDomain(it)!! }
    }
}
