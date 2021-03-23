package com.game.database.rawg.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.game.database.rawg.common.base.BaseFragment
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.databinding.FragmentFavoriteBinding
import com.game.database.rawg.extension.waitForTransition
import com.game.database.rawg.ui.adapters.favourite.FavoriteAdapter
import com.game.database.rawg.ui.adapters.listeners.FavoriteListener
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), FavoriteListener {

    private var favoriteAdapter: FavoriteAdapter? = null
    private val favoriteViewModel by viewModel<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = FavoriteAdapter(this)

        binding.apply {
            adapter = favoriteAdapter
        }

        waitForTransition(favoriteList)
        observeData()
    }

    private fun observeData() {
        with(favoriteViewModel) {
            getAllList.observe(viewLifecycleOwner, {
                favoriteAdapter?.submitList(it)
            })
        }
    }

    override fun openDetailFragment(result: GameResult, poster: View) {
        poster.findNavController()
            .navigate(
                FavoriteFragmentDirections.actionFavouriteFragmentToDetailGameFragment(
                    result
                ), FragmentNavigatorExtras(poster to "detail")
            )
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?,
    ) = FragmentFavoriteBinding.inflate(inflater, parent, false)

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteAdapter = null
    }

}