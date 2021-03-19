package com.game.database.rawg.ui.fragment.detail.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.game.database.rawg.databinding.DialogStoresBinding
import com.game.database.rawg.ui.adapters.stores.StoresAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StoresBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogStoresBinding
    private val storesAdapter by lazy { StoresAdapter() }
    private val args by navArgs<StoresBottomDialogArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogStoresBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = storesAdapter
        }

        storesAdapter.submitList(args.store.toList())
    }

}