package com.game.database.rawg.ui.adapters.screenshots

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.data.model.list.ScreenshotsResult
import com.game.database.rawg.databinding.ItemScreenshotsBinding

class ScreenshotsAdapter : RecyclerView.Adapter<ScreenshotsAdapter.ScreenshotsViewHolder>() {

    private val items = mutableListOf<ScreenshotsResult>()

    fun setData(list: List<ScreenshotsResult>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ScreenshotsViewHolder(
            ItemScreenshotsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        holder.binding.apply {
            item = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class ScreenshotsViewHolder(val binding: ItemScreenshotsBinding) :
        RecyclerView.ViewHolder(binding.root)

}