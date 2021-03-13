package com.game.database.rawg.common.bindings

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.core.view.marginEnd
import androidx.core.view.size
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.game.database.rawg.extension.hide
import com.game.database.rawg.extension.visible
import com.game.database.rawg.common.utils.State
import com.game.database.rawg.data.model.detail.Genre
import com.game.database.rawg.data.model.list.TagsResponse
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("fillingView")
fun View.setFillingView(state: State) {
    when (this) {
        is ProgressBar -> {
            if (state == State.LOADING) {
                this.visible()
            } else {
                this.hide()
            }
        }
        is NestedScrollView -> {
            if (state == State.SUCCESS) {
                this.visible()
            } else {
                this.hide()
            }
        }
        is RecyclerView -> {
            if (state == State.SUCCESS) {
                this.visible()
            } else {
                this.hide()
            }
        }
        else -> {
            if (state == State.ERROR) {
                this.visible()
            } else {
                this.hide()
            }
        }
    }
}

@BindingAdapter("htmlText")
fun TextView.setHtmlText(text: String?) {
    this.text = text?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT) }
}

@BindingAdapter("dateFormat")
fun TextView.setDateFormat(date: String?) {
    this.text = date
}

@BindingAdapter("genres")
fun ChipGroup.setGenres(genre: List<Genre>?) {
    if (genre != null) {
        for (g in genre) {
            val chip = Chip(context)
            chip.text = g.name
            this.addView(chip)
        }
    }
}

@BindingAdapter("tags")
fun ChipGroup.setTags(tags: List<TagsResponse>) {
    for (t in tags) {
        val chip = Chip(context)
        chip.text = t.tags
        this.addView(chip)
    }
}