package com.example.nactemsearch

import com.example.nactemsearch.network.model.ResultItemDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Communicates with the backend to obtain data using the coroutines.
 */
interface NectemSearchService {

    @GET("dictionary.py")
    suspend fun getSearchResult(
        @Query("sf") sf: String
    ): List<ResultItemDto>
}