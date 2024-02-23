package com.example.nactemsearch.features.search

import com.example.nactemsearch.domain.model.Longform
import com.example.nactemsearch.domain.util.Response

interface SearchRepo {

    suspend fun getSearchList(sf: String): Response<List<Longform>>
}