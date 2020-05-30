package com.home.futuretalentnetworktestdemo2.pagination.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.home.futuretalentnetworktestdemo2.databinding.ActivityPaginationRecyclerViewItemBinding
import com.home.futuretalentnetworktestdemo2.pagination.model.pojo.RepositoriesPojo
import com.home.futuretalentnetworktestdemo2.pagination.view.adapter.PaginationAdapter.ViewHolder

class PaginationAdapter : PagedListAdapter<RepositoriesPojo, ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<RepositoriesPojo>() {
                override fun areItemsTheSame(
                    oldItem: RepositoriesPojo, newItem: RepositoriesPojo
                ): Boolean = oldItem.html_url == newItem.html_url

                override fun areContentsTheSame(
                    oldItem: RepositoriesPojo, newItem: RepositoriesPojo
                ): Boolean = oldItem == newItem
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            ActivityPaginationRecyclerViewItemBinding.inflate(
                layoutInflater, parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pojo = getItem(position)
        holder.bindData(pojo)
    }

    class ViewHolder(binding: ActivityPaginationRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val nameTextView: TextView = binding.textViewName
        private val createdTextView: TextView = binding.textViewCreated

        fun bindData(pojo: RepositoriesPojo?) {
            pojo?.apply {
                nameTextView.text = name
                createdTextView.text = created_at
            }
        }
    }
}
