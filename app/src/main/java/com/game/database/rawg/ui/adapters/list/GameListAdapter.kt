package com.game.database.rawg.ui.adapters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.common.utils.Constants.Companion.LOADING_ITEM
import com.game.database.rawg.common.utils.Constants.Companion.MOVIE_ITEM
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.databinding.ItemGameBinding
import com.game.database.rawg.ui.fragment.list.GameListFragmentDirections
import com.game.database.rawg.common.utils.GameDiffUtils

class GameListAdapter :
    PagingDataAdapter<GameResult, GameListAdapter.GameListViewHolder>(GameDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameListViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.binding.apply {
            gameResult = getItem(position)
            executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount -> MOVIE_ITEM
            else -> LOADING_ITEM
        }
    }

    class GameListViewHolder(val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                cardItem.setOnClickListener {
                    it.findNavController()
                        .navigate(
                            GameListFragmentDirections.actionGameListFragmentToDetailGameFragment(
                                gameResult
                            ),
                            FragmentNavigatorExtras(poster to "detail")
                        )
                }
            }
        }

    }

}