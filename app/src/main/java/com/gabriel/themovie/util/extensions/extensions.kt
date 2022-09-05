package com.gabriel.themovie.util.extensions

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

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

fun String.limitDescription(limit: Int): String {
    if (this.length > limit) {
        val first = 0
        return "${this.substring(first, limit)}..."
    }
    return this
}
