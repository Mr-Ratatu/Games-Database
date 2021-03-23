package com.game.database.rawg.ui.fragment.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.game.database.rawg.R
import com.game.database.rawg.ui.adapters.list.GameListAdapter
import com.game.database.rawg.ui.adapters.list.GameLoadStateAdapter
import com.game.database.rawg.common.base.BaseFragment
import com.game.database.rawg.common.utils.Constants
import com.game.database.rawg.common.utils.Constants.Companion.MIN_QUERY_LENGTH
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.databinding.FragmentListGameBinding
import com.game.database.rawg.extension.hideKeyboard
import com.game.database.rawg.extension.waitForTransition
import kotlinx.android.synthetic.main.fragment_list_game.*
import kotlinx.android.synthetic.main.include_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameListFragment : BaseFragment<FragmentListGameBinding>(), GameListAdapter.GameListListener {

    private var listAdapter: GameListAdapter? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    private val gameListViewModel by viewModel<GameListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        listAdapter = GameListAdapter(this)
        gridLayoutManager = GridLayoutManager(requireContext(), 2)

        binding.layoutManager = spanSizeLayoutManager()

        recycler.apply {
            adapter = listAdapter?.withLoadStateHeaderAndFooter(
                header = GameLoadStateAdapter { listAdapter?.retry() },
                footer = GameLoadStateAdapter { listAdapter?.retry() }
            )
            waitForTransition(this)
        }

        observeData()
        refreshList()
    }

    private fun observeData() {
        with(gameListViewModel) {
            pagingGames.observe(viewLifecycleOwner, {
                listAdapter?.submitData(lifecycle, it)
            })
        }
    }

    private fun spanSizeLayoutManager(): GridLayoutManager {
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (Constants.LOADING_ITEM) {
                    listAdapter?.getItemViewType(position) -> 1
                    else -> 2
                }
            }
        }
        return gridLayoutManager
    }

    private fun refreshList() {
        refresh.setOnRefreshListener {
            gameListViewModel.refreshList()
        }

        listAdapter?.addLoadStateListener {
            refresh?.isRefreshing = it.source.refresh is LoadState.Loading
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        val searchMenu = menu.findItem(R.id.menu_action_search)
        val searchView = searchMenu.actionView as SearchView

        checkOnEmptySearchView(searchMenu, searchView)
        //setActionExpandScrollView(searchMenu)
        setUpScrollView(searchView)
    }

    private fun setUpScrollView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                view?.hideKeyboard()
                searchView.clearFocus()
                gameListViewModel.search(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.length >= MIN_QUERY_LENGTH) {
                    gameListViewModel.search(query)
                }
                return true
            }
        })
    }

    private fun setActionExpandScrollView(menuItem: MenuItem) {
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                gameListViewModel.search("")
                return true
            }

        })
    }

    override fun onGameResultClicked(result: GameResult?, poster: View) {
        poster.findNavController().navigate(
            GameListFragmentDirections.actionGameListFragmentToDetailGameFragment(
                result
            ),
            FragmentNavigatorExtras(poster to "detail")
        )
    }

    private fun checkOnEmptySearchView(menuItem: MenuItem, searchView: SearchView) {
        val searchField = gameListViewModel.queryField
        if (searchField.isNotEmpty()) {
            menuItem.expandActionView()
            searchView.clearFocus()
            searchView.setQuery(gameListViewModel.queryField, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listAdapter = null
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?,
    ) = FragmentListGameBinding.inflate(inflater, parent, false)

}