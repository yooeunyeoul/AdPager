package com.dongeul.adpager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dongeul.adpager.databinding.ItemViewBinding
import com.dongeul.adpager.model.Content

class PagerAdapter : ListAdapter<Content, PagerAdapter.ContentViewHolder>(DiffCallback) {
    lateinit var listener : (Content)->(Unit)

    inner class ContentViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Content) {
            binding.content = item
            Glide.with(itemView.context).load(item.imageUrl).into(binding.ivBg)
            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}