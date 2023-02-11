package io.nguyenhuynhdev.admin.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "documents")
data class Document(
    @SerializedName("document_id")
    @PrimaryKey val documentId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String
)