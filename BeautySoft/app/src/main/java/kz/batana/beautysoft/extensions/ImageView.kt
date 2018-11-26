package kz.batana.beautysoft.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


internal fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
            .load(url)
            .into(this)
}