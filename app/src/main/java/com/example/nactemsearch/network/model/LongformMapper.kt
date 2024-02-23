package com.example.nactemsearch.network.model

import com.example.nactemsearch.domain.model.Longform
import com.example.nactemsearch.domain.util.Mapper
import javax.inject.Inject

class LongformMapper @Inject constructor(): Mapper<LongformDto, Longform> {

    override fun mapToDomainModel(dto: LongformDto): Longform {
        return Longform(
            lf = dto.lf
        )
    }

    fun toDomainList(list: List<LongformDto>): List<Longform> {
        return list.map { mapToDomainModel(it) }
    }
}