package com.capstone.hydroandroid.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.search.SearchResponse
import com.capstone.hydroandroid.source.search.SearchRepository

class SearchViewModel(private val repository: SearchRepository): ViewModel() {
    fun getSearchedBlog (query: String) : LiveData<EventResult<SearchResponse>> {
        return repository.getSearchedBlog(query)
    }
}