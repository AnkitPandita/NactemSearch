package com.example.nactemsearch.features.search

import com.example.nactemsearch.NectemSearchService
import com.example.nactemsearch.domain.model.Longform
import com.example.nactemsearch.domain.util.Response
import com.example.nactemsearch.network.model.LongformMapper
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val nectemSearchService: NectemSearchService,
    private val mapper: LongformMapper
) : SearchRepo {

    override suspend fun getSearchList(sf: String): Response<List<Longform>> {
        val result = kotlin.runCatching { nectemSearchService.getSearchResult(sf) }
        result.onSuccess {
            if (it.isEmpty()) {
                return Response.Error("No results found!")
            }
            return Response.Success(it[0].let { resultItemDto ->
                mapper.toDomainList(resultItemDto.lfs)
            })
        }
        result.onFailure {
            return Response.Error(it.message.toString())
        }
        return Response.Error("Not responding!")
    }
}