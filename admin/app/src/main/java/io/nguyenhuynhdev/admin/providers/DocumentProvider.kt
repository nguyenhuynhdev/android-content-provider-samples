package io.nguyenhuynhdev.admin.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import io.nguyenhuynhdev.admin.database.AppDatabase
import io.nguyenhuynhdev.admin.database.Document
import io.nguyenhuynhdev.admin.database.DocumentDao

class DocumentProvider : ContentProvider() {

    companion object {
        private const val DOCUMENT = 1
        private const val DOCUMENTS = 2
        private const val DOCUMENT_PATH = "document/#"
        private const val DOCUMENTS_PATH = "document"
        private const val AUTHORITY = "io.nguyenhuynhdev.admin.providers"
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, DOCUMENT_PATH, DOCUMENT)
            uriMatcher.addURI(AUTHORITY, DOCUMENTS_PATH, DOCUMENTS)
        }
    }

    private lateinit var appDatabase: AppDatabase
    private lateinit var documentDao: DocumentDao

    override fun onCreate(): Boolean {
        context?.let {
            appDatabase = Room.databaseBuilder(it, AppDatabase::class.java, "DB.").build()
            documentDao = appDatabase.documentDao()
            return true
        }
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            DOCUMENT -> {
                val id = uri.lastPathSegment?.toInt()
                if (id != null) {
                    documentDao.getCursorDocumentById(id)
                } else {
                    null
                }
            }
            DOCUMENTS -> {
                documentDao.getAllCursorDocuments()
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            DOCUMENT -> "vnd.android.cursor.item/vnd.$AUTHORITY.user"
            DOCUMENTS -> "vnd.android.cursor.dir/vnd.$AUTHORITY.user"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val documentId = values!!.getAsInteger("documentId")
        val content = values.getAsString("content")
        val title = values.getAsString("title")
        val description = values.getAsString("description")
        documentDao.insertDocuments(Document(documentId, content, title, description))
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.parse("$AUTHORITY.document/$documentId")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val documentId = uri.lastPathSegment!!.toInt()
        documentDao.deleteDocument(Document(documentId, "", "", ""))
        context!!.contentResolver.notifyChange(uri, null)
        return 1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val documentId = uri.lastPathSegment!!.toInt()
        val content = values!!.getAsString("content")
        val title = values.getAsString("title")
        val description = values.getAsString("description")
        documentDao.updateDocument(Document(documentId, content, title, description))
        context!!.contentResolver.notifyChange(uri, null)
        return 1
    }
}