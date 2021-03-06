package com.game.database.rawg.presentation.ui.fragment.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.game.database.rawg.presentation.base.fragment.BaseFragment
import com.game.database.rawg.databinding.FragmentGameDetailBinding
import com.game.database.rawg.common.utils.State
import com.game.database.rawg.data.model.detail.StoreResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.model.list.ScreenshotsResult
import com.game.database.rawg.presentation.extension.addOrRemoveFavorite
import com.game.database.rawg.presentation.ui.adapters.GeneralAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_persistent_dialog.*

@AndroidEntryPoint
class DetailGameFragment : BaseFragment<FragmentGameDetailBinding>() {

    private val detailViewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailGameFragmentArgs>()
    private val resultDetail by lazy { args.gameResult }
    private var screenshotsAdapter: GeneralAdapter<ScreenshotsResult>? = null
    private var similarAdapter: GeneralAdapter<GameResult>? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = extras
        sharedElementReturnTransition = extras
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenshotsAdapter = GeneralAdapter()
        similarAdapter = GeneralAdapter()
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        binding.apply {
            state = State.LOADING
            adapter = screenshotsAdapter
            result = resultDetail
            vm = detailViewModel
            similar = similarAdapter
        }

        observableData(resultDetail?.gameId)
        bottomSheetCallBack()

        resultDetail?.let {
            screenshotsAdapter?.submitList(it.screenshots)
            addToFavorite.addOrRemoveFavorite(detailViewModel.isFavorite(it))
        }
    }

    private fun observableData(id: Int?) {
        with(detailViewModel) {
            getDetailsGame(id)
            getSimilarGames(id)

            isFavorite.observe(viewLifecycleOwner, {
                addToFavorite.addOrRemoveFavorite(it)
            })

            loadDetail.observe(viewLifecycleOwner, {
                if (it.state == State.SUCCESS) {
                    binding.item = it.data
                }

                binding.state = it.state
            })

            similarGames.observe(viewLifecycleOwner, {
                similarAdapter?.submitList(it)
            })

            shareData.observe(viewLifecycleOwner, {
                shareDataIntent(it)
            })

            stores.observe(viewLifecycleOwner, {
                showStoresClick(it.toTypedArray())
            })
        }
    }

    private fun bottomSheetCallBack() {
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN)
                    view?.findNavController()?.popBackStack()
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    private fun showStoresClick(stores: Array<StoreResponse>) {
        view?.findNavController()
            ?.navigate(DetailGameFragmentDirections.actionDetailGameFragmentToStoresBottomDialog(
                stores))
    }

    private fun shareDataIntent(data: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, data)
            type = "text/plain"
        }

        startActivity(shareIntent)
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?,
    ) = FragmentGameDetailBinding.inflate(inflater, parent, false)

    override fun onDestroyView() {
        super.onDestroyView()
        similarAdapter = null
        screenshotsAdapter = null
    }

}