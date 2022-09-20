package com.gabriel.themovie.util.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import coil.load
import com.gabriel.themovie.R
import com.gabriel.themovie.util.constants.ConstantsView

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

fun ImageView.tentaCarregar(url: String? = null){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(androidx.appcompat.R.color.material_grey_600)
    }
}
