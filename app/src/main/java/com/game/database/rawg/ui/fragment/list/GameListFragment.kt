package com.game.database.rawg.ui.fragment.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.game.database.rawg.R
import com.game.database.rawg.ui.adapters.list.GameListAdapter
import com.game.database.rawg.ui.adapters.list.GameLoadStateAdapter
import com.game.database.rawg.common.base.BaseFragment
import com.game.database.rawg.common.utils.Constants
import com.game.database.rawg.common.utils.Constants.Companion.LAST_QUERY
import com.game.database.rawg.common.utils.Constants.Companion.MIN_QUERY_LENGTH
import com.game.database.rawg.databinding.FragmentListGameBinding
import com.game.database.rawg.extension.hideKeyboard
import com.game.database.rawg.extension.waitForTransition
import kotlinx.android.synthetic.main.fragment_list_game.*
import kotlinx.android.synthetic.main.include_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameListFragment : BaseFragment<FragmentListGameBinding>() {

    private val listAdapter by lazy { GameListAdapter() }
    private val gridLayoutManager by lazy { GridLayoutManager(requireContext(), 2) }
    private val gameListViewModel by viewModel<GameListViewModel>()
    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        binding.apply {
            recycler.adapter = listAdapter.withLoadStateHeaderAndFooter(
                header = GameLoadStateAdapter { listAdapter.retry() },
                footer = GameLoadStateAdapter { listAdapter.retry() }
            )
            waitForTransition(recycler)
        }

        observeData()
        refreshList()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_QUERY, searchView.query.trim().toString())
    }

    private fun observeData() {
        with(gameListViewModel) {
            pagingGames.observe(viewLifecycleOwner, {
                listAdapter.submitData(lifecycle, it)
            })
        }
    }

    private fun spanSizeLayoutManager() {
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (Constants.LOADING_ITEM) {
                    listAdapter.getItemViewType(position) -> 1
                    else -> 2
                }
            }
        }
    }

    private fun refreshList() {
        refresh.setOnRefreshListener {
            gameListViewModel.refreshList()
        }

        listAdapter.addLoadStateListener {
            refresh.isRefreshing = it.source.refresh is LoadState.Loading
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        setUpScrollView(menu)
    }

    private fun setUpScrollView(menu: Menu) {
        val searchMenu = menu.findItem(R.id.menu_action_search)
        searchView = searchMenu.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                gameListViewModel.search(query)
                recycler.scrollToPosition(0)
                view?.hideKeyboard()
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.length >= MIN_QUERY_LENGTH) {
                    gameListViewModel.search(query)
                    recycler.scrollToPosition(0)
                }
                return true
            }
        })
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = FragmentListGameBinding.inflate(inflater, parent, false)

}