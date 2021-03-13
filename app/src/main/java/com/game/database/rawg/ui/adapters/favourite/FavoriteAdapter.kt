package com.game.database.rawg.ui.adapters.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.databinding.ItemGameBinding
import com.game.database.rawg.ui.fragment.favorite.FavoriteFragmentDirections

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavouriteViewHolder>() {

    private val items = mutableListOf<GameResult>()

    fun setList(list: List<GameResult>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavouriteViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.binding.apply {
            gameResult = items[position]

            cardItem.setOnClickListener {
                it.findNavController().navigate(
                    FavoriteFragmentDirections.actionFavouriteFragmentToDetailGameFragment(
                        gameResult
                    ),
                    FragmentNavigatorExtras(poster to "detail")
                )
            }

            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class FavouriteViewHolder(val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root)

}