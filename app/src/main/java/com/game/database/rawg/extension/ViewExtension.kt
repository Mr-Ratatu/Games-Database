package com.game.database.rawg.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.game.database.rawg.R
import com.google.android.material.button.MaterialButton

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun MaterialButton.addOrRemoveFavorite(isFavorite: Boolean) {
    if (isFavorite) {
        this.apply {
            text = context.getString(R.string.remove_to_list)
            icon = context.getDrawable(R.drawable.ic_cancel)
        }
    } else {
        this.apply {
            text = context.getString(R.string.add_to_list)
            icon = context.getDrawable(R.drawable.ic_add)
        }
    }
}