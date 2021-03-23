package com.game.database.rawg.common.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imgTransition")
fun ImageView.setImgTransition(url: String?) {
    Glide
        .with(context)
        .load(url)
        .apply { RequestOptions().dontTransform() }
        .transition(DrawableTransitionOptions())
        .into(this)
}

@BindingAdapter("img")
fun ImageView.setImg(url: String?) {
    Glide
        .with(context)
        .load(url)
        .into(this)

}