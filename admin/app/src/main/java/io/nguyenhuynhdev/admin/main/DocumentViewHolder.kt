package io.nguyenhuynhdev.admin.main

import androidx.recyclerview.widget.RecyclerView
import io.nguyenhuynhdev.admin.database.Document
import io.nguyenhuynhdev.admin.databinding.DocumentViewholderBinding

class DocumentViewHolder constructor(private val binding: DocumentViewholderBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(document: Document){
            binding.tvTitle.text = document.title
            binding.tvContent.text = document.content
        }
}