package io.nguyenhuynhdev.admin.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface DocumentDao {

    @Query("SELECT * FROM documents")
    fun getAllDocuments(): LiveData<List<Document>>

    @Query("SELECT * FROM documents")
    fun getAllCursorDocuments(): Cursor

    @Query("SELECT 1 FROM documents WHERE documentId = :id")
    fun getCursorDocumentById(id: Int): Cursor

    @Query("DELETE FROM documents WHERE documentId = :id")
    fun deleteDocumentById(id: Int)

//    @Query("SELECT * FROM documents WHERE label LIKE :query")
//    fun pagingSource(query: String): PagingSource<Int, Document>

    @Query("SELECT * FROM documents ORDER BY documentId ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedDocumentList(limit: Int, offset: Int): List<Document>

    @Delete
    fun deleteDocument(document: Document)

    @Update
    fun updateDocument(document: Document)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDocuments(vararg document: Document)

}