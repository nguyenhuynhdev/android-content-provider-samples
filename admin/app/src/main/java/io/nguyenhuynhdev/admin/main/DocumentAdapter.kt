package io.nguyenhuynhdev.admin.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.nguyenhuynhdev.admin.database.Document
import io.nguyenhuynhdev.admin.databinding.DocumentViewholderBinding

class DocumentAdapter : PagingDataAdapter<Document, DocumentViewHolder>(DOCUMENT_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        return DocumentViewHolder(
            DocumentViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    companion object {
        private val DOCUMENT_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Document>() {
            override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean =
                oldItem.documentId == newItem.documentId

            override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean =
                oldItem == newItem
        }
    }
}