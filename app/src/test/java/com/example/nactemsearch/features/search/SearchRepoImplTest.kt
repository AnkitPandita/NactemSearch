package com.example.nactemsearch.features.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nactemsearch.CoroutineTestRule
import com.example.nactemsearch.NectemSearchService
import com.example.nactemsearch.network.model.LongformDto
import com.example.nactemsearch.network.model.LongformMapper
import com.example.nactemsearch.network.model.ResultItemDto
import com.example.nactemsearch.network.model.VariationDto
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Test class for repository.
 */
class SearchRepoImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    lateinit var sut: SearchRepoImpl

    @Mock
    lateinit var nectemSearchService: NectemSearchService

    @Mock
    lateinit var mapper: LongformMapper

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = SearchRepoImpl(nectemSearchService, mapper)
    }

    @Test
    fun testGetStoreList() = runTest {
        val variationDto = VariationDto("dengue fever", 1, 1)
        val longformDto = LongformDto("dengue fever", 1, 1, listOf(variationDto, variationDto))
        val resultItemDto = ResultItemDto("df", listOf(longformDto, longformDto))
        val resultList = listOf(resultItemDto)
        Mockito.`when`(nectemSearchService.getSearchResult(any())).thenReturn(resultList)
        val response = sut.getSearchList(any())
        //assertEquals(true, response is Response<List<Store>>)
        assertEquals(true, response.data!!.size == 1)
        //assertEquals("name1", response.data!![1].name)
    }
}