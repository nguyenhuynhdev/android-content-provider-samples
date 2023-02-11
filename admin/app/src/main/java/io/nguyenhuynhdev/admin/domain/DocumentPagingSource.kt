package io.nguyenhuynhdev.admin.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.nguyenhuynhdev.admin.database.Document
import io.nguyenhuynhdev.admin.database.DocumentDao
import io.nguyenhuynhdev.admin.database.GitHubService
import javax.inject.Inject

class DocumentPagingSource @Inject constructor(private val documentDao: DocumentDao, private val gitHubService: GitHubService) :
    PagingSource<Int, Document>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        val page = params.key ?: 0
        return try {
            var entities = documentDao.getPagedDocumentList(params.loadSize, page * params.loadSize)
            if (entities.isEmpty()){
                // simulate page loading
//                if (page != 0) delay(1000)
                entities = gitHubService.getDocuments()
                LoadResult.Page(
                    data = entities,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (entities.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Page(
                    data = entities,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (entities.isEmpty()) null else page + 1)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}