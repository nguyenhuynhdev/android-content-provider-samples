package io.nguyenhuynhdev.admin.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.nguyenhuynhdev.admin.database.DocumentDao
import io.nguyenhuynhdev.admin.database.GitHubService
import io.nguyenhuynhdev.admin.domain.DocumentPagingSource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val documentPagingSource: DocumentPagingSource
) : ViewModel() {
    val data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        documentPagingSource
    }.flow.cachedIn(viewModelScope)

}