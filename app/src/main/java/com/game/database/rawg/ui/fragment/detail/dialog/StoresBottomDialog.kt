package com.game.database.rawg.ui.fragment.detail.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.game.database.rawg.data.model.detail.StoreResponse
import com.game.database.rawg.databinding.DialogStoresBinding
import com.game.database.rawg.ui.adapters.GeneralAdapter
import com.game.database.rawg.ui.adapters.listeners.StoresListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StoresBottomDialog : BottomSheetDialogFragment(), StoresListener {

    private lateinit var binding: DialogStoresBinding
    private var storesAdapter: GeneralAdapter<StoreResponse>? = null
    private val args by navArgs<StoresBottomDialogArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogStoresBinding.inflate(inflater, container, false)
        storesAdapter = GeneralAdapter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = storesAdapter
        }

        storesAdapter?.submitList(args.store.toList())
    }

    override fun onClickLinkStores(store: StoreResponse) {
        val openUrl = Intent(Intent.ACTION_VIEW)
        openUrl.data = Uri.parse(store.url)
        startActivity(openUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        storesAdapter = null
    }
}