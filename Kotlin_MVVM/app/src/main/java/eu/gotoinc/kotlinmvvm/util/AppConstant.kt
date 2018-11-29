package eu.gotoinc.kotlinmvvm.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

const val HOME_VM = "home_viewmodel"

@BindingAdapter("setImage")
fun setImage(iv: AppCompatImageView, src: String) {
    Picasso.get().load(src).centerCrop().fit().into(iv)
}
