package io.nguyenhuynhdev.admin.database

import retrofit2.http.GET

interface GitHubService {

    @GET("documents.json")
    suspend fun getDocuments(): List<Document>
}