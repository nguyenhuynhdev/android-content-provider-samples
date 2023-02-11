package io.nguyenhuynhdev.admin.domain

import androidx.lifecycle.SavedStateHandle
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.nguyenhuynhdev.admin.database.Document
import io.nguyenhuynhdev.admin.database.DocumentDao
import io.nguyenhuynhdev.admin.database.GitHubService
import javax.inject.Inject

//private const val GITHUB_STARTING_PAGE_INDEX = 1
//@OptIn(ExperimentalPagingApi::class)
//class GithubRemoteMediator @Inject constructor(
//    private val gitHubService: GitHubService,
//    private val documentDao: DocumentDao,
//    private val savedStateHandle: SavedStateHandle
//): RemoteMediator<Int, Document>() {
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, Document>
//    ): MediatorResult {
//
//    }
//
//
//}