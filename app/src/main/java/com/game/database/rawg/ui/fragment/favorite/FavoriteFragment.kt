package com.game.database.rawg.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.database.rawg.ui.adapters.favourite.FavoriteAdapter
import com.game.database.rawg.common.base.BaseFragment
import com.game.database.rawg.databinding.FragmentFavoriteBinding
import com.game.database.rawg.extension.waitForTransition
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val favouriteAdapter by lazy { FavoriteAdapter() }
    private val favouriteViewModel by viewModel<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = favouriteAdapter
        }

        waitForTransition(favoriteList)
        observeData()
    }

    private fun observeData() {
        with(favouriteViewModel) {
            getAllList.observe(viewLifecycleOwner, {
                favouriteAdapter.setList(it)
            })
        }
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = FragmentFavoriteBinding.inflate(inflater, parent, false)
}