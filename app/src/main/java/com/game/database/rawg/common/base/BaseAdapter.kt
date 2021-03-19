package com.game.database.rawg.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.BR

abstract class BaseAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val baseAdapterListener: BaseAdapterListener<T>? = null
) : ListAdapter<T, BaseAdapter.BaseViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return BaseViewHolder(baseAdapterListener, binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    class BaseViewHolder<T>(
        private val listener: BaseAdapterListener<T>?,
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(BR.items, item)
            listener?.let {
                binding.setVariable(BR.listener, it)
            } ?: binding.setVariable(BR.listener, object : BaseAdapterListener<T> {})
            binding.executePendingBindings()
        }
    }
}