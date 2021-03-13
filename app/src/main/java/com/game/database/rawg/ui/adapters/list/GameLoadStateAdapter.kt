package com.game.database.rawg.ui.adapters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.R
import com.game.database.rawg.databinding.ItemLoadStateFooterBinding

class GameLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<GameLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_load_state_footer,
                parent,
                false
            ), retry
        )

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
            textViewError.isVisible = loadState !is LoadState.Loading
            buttonRetry.isVisible = loadState is LoadState.Error
        }
    }

    class LoadStateViewHolder(
        val binding: ItemLoadStateFooterBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

    }

}