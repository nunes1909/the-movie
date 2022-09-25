package com.gabriel.themovie.util.base

interface ViewMapper<View, Domain> {
    fun mapToDomain(type: View): Domain

    fun mapToView(type: Domain): View

    fun mapToDomain(view: List<View?>): List<Domain?> {
        return view.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapToView(domain: List<Domain?>): List<View?> {
        return domain.map { if (it == null) null else mapToView(it) }
    }

    fun mapToDomainNonNull(view: List<View>): List<Domain> {
        return view.map { mapToDomain(it) }
    }

    fun mapToViewNonNull(domain: List<Domain>): List<View> {
        return domain.map { mapToView(it)!! }
    }
}
