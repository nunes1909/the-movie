package com.gabriel.themovie.util.base

interface ViewMapper<View, Domain> {
    fun mapToDomain(type: View): Domain

    fun mapToView(type: Domain): View

    fun mapToDomain(entity: List<View?>): List<Domain?> {
        return entity.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapToView(domain: List<Domain?>): List<View?> {
        return domain.map { if (it == null) null else mapToView(it) }
    }

    fun mapToDomainNonNull(entity: List<View>): List<Domain> {
        return entity.map { mapToDomain(it) }
    }

    fun mapToViewNonNull(domain: List<Domain>): List<View> {
        return domain.map { mapToView(it)!! }
    }
}
