package com.gabriel.themovie.util.extensions

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.gabriel.themovie.R

fun Fragment.toast(mensagem: String) {
    Toast.makeText(
        requireContext(),
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String.limitValue(limit: Int, ellipsize: Boolean): String {
    val ellEnd = if (ellipsize) "..." else ""
    if (this.length > limit) {
        val first = 0
        return "${this.substring(first, limit)}$ellEnd"
    }
    return this
}

fun ImageView.tentaCarregar(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(androidx.appcompat.R.color.material_grey_600)
    }
}
