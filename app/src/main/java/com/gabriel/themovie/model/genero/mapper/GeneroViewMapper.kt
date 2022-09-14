package com.gabriel.themovie.model.genero.mapper

import com.gabriel.domain.genero.model.GeneroDomain
import com.gabriel.themovie.model.genero.model.GeneroView
import com.gabriel.themovie.util.base.ViewMapper

class GeneroViewMapper : ViewMapper<GeneroView, GeneroDomain> {
    override fun mapToDomain(type: GeneroView): GeneroDomain {
        return GeneroDomain(
            id = type.id,
            name = type.name
        )
    }

    override fun mapFromDomain(type: GeneroDomain): GeneroView {
        return GeneroView(
            id = type.id,
            name = type.name
        )
    }
}
