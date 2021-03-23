package com.game.database.rawg.ui.adapters.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.common.utils.Constants.Companion.LOADING_ITEM
import com.game.database.rawg.common.utils.Constants.Companion.GAME_ITEM
import com.game.database.rawg.common.base.DiffUtils
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.databinding.ItemGameBinding

class GameListAdapter(private var listener: GameListListener) :
    PagingDataAdapter<GameResult, GameListAdapter.GameListViewHolder>(DiffUtils()) {

    interface GameListListener {
        fun onGameResultClicked(result: GameResult?, poster: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameListViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount -> GAME_ITEM
            else -> LOADING_ITEM
        }
    }

    class GameListViewHolder(val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: GameResult?, listener: GameListListener) {
            binding.apply {
                root.setOnClickListener { listener.onGameResultClicked(result, poster) }
                items = result
                executePendingBindings()
            }
        }
    }

}