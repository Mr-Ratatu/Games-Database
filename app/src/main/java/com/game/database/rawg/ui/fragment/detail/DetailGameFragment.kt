package com.game.database.rawg.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.game.database.rawg.ui.adapters.screenshots.ScreenshotsAdapter
import com.game.database.rawg.common.base.BaseFragment
import com.game.database.rawg.databinding.FragmentGameDetailBinding
import com.game.database.rawg.common.utils.State
import com.game.database.rawg.data.model.list.GameResult
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_persistent_dialog.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameFragment : BaseFragment<FragmentGameDetailBinding>() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>
    private val detailViewModel by viewModel<DetailViewModel>()
    private val args by navArgs<DetailGameFragmentArgs>()
    private val screenshotsAdapter by lazy { ScreenshotsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = extras
        sharedElementReturnTransition = extras
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        binding.apply {
            state = State.LOADING
            adapter = screenshotsAdapter
            result = args.gameResult
            vm = detailViewModel
        }

        observableData(args.gameResult?.gameId)
        bottomSheetCallBack()

        args.gameResult?.let {
            screenshotsAdapter.setData(it.screenshots)
        }
    }

    private fun observableData(id: Int?) {
        with(detailViewModel) {
            getDetailsGame(id)

            loadDetail.observe(viewLifecycleOwner, {
                if (it.state == State.SUCCESS) {
                    binding.item = it.data
                }

                binding.state = it.state
            })
        }
    }

    private fun addToFavoriteClick(result: GameResult) {
        addToFavorite.setOnClickListener {
            with(detailViewModel) {
                if (isFavorite(result))
                    delete(result)
                else
                    insert(result)
            }
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

    override fun getDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = FragmentGameDetailBinding.inflate(inflater, parent, false)

}