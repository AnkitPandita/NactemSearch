package com.example.nactemsearch.features.search

import com.example.nactemsearch.NectemSearchService
import com.example.nactemsearch.domain.model.Longform
import com.example.nactemsearch.network.model.LongformDto
import com.example.nactemsearch.network.model.LongformMapper
import com.example.nactemsearch.network.model.ResultItemDto
import com.example.nactemsearch.network.model.VariationDto
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Test class for repository.
 */
class SearchRepoImplTest {

    @InjectMocks
    lateinit var sut: SearchRepoImpl

    @Mock
    lateinit var nactemSearchService: NectemSearchService

    @Mock
    lateinit var mapper: LongformMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetStoreListNotEmpty() = runTest {
        val variationDto = VariationDto("dengue fever", 1, 1)
        val longformDto = LongformDto("dengue fever", 1, 1, listOf(variationDto, variationDto))
        val resultItemDto = ResultItemDto("df", listOf(longformDto, longformDto))
        val longformList = listOf(Longform("dengue fever"), Longform("dengue fever"))
        Mockito.`when`(nactemSearchService.getSearchResult("abc")).thenReturn(listOf(resultItemDto))
        Mockito.`when`(mapper.toDomainList(resultItemDto.lfs)).thenReturn(longformList)
        val response = sut.getSearchList("abc")
        assertEquals(true, response.data!!.size == 2)
        assertEquals("dengue fever", response.data!![1].lf)
    }

    @Test
    fun testGetStoreListEmpty() = runTest {
        Mockito.`when`(nactemSearchService.getSearchResult("dfdfdf")).thenReturn(emptyList())
        val response = sut.getSearchList("dfdfdf")
        assertEquals("No results found!", response.errorMessage)
    }
}