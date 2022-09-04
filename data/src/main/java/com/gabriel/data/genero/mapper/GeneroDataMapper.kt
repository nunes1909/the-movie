package com.gabriel.data.genero.mapper

import com.gabriel.data.genero.model.GeneroData
import com.gabriel.data.util.base.DataMapper
import com.gabriel.domain.genero.model.GeneroDomain

class GeneroDataMapper : DataMapper<GeneroData, GeneroDomain> {
    override fun mapToDomain(type: GeneroData): GeneroDomain {
        return GeneroDomain(
            id = type.id,
            name = type.name
        )
    }

    override fun mapFromDomain(type: GeneroDomain): GeneroData {
        return GeneroData(
            id = type.id,
            name = type.name
        )
    }
}
