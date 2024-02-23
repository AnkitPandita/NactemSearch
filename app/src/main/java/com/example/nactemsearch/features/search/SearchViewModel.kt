package com.example.nactemsearch.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nactemsearch.domain.model.Longform
import com.example.nactemsearch.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepoImpl: SearchRepoImpl) :
    ViewModel() {

    private val _searchList =
        MutableStateFlow<Response<List<Longform>>>(Response.Success(emptyList()))
    val searchList = _searchList.asStateFlow()

    fun getList(sf: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchList.emit(Response.Loading())
            if (sf.isEmpty()) {
                _searchList.emit(Response.Success(emptyList()))
            } else {
                _searchList.emit(searchRepoImpl.getSearchList(sf))
            }
        }
    }
}