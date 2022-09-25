package com.gabriel.themovie.ui.view.detalhes

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.gabriel.themovie.databinding.DialogInfoMovieBinding
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.extensions.tentaCarregar

class DetalhesInfoDialog : DialogFragment() {

    private val args: DetalhesFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        builder.setView(createView())

        return builder.create()
    }

    override fun onStart() {
        super.onStart()

        val mDialog = dialog

        if (mDialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            mDialog.window?.setLayout(width, height)
        }
    }

    private fun createView(): View {
        val binding = DialogInfoMovieBinding.inflate(layoutInflater)
        configuraComponentes(binding)
        return binding.root
    }

    private fun configuraComponentes(binding: DialogInfoMovieBinding) {
        binding.tvDialogInfoTitle.text = args.movieView.title
        binding.tvDialogInfoDescription.text = args.movieView.description
        binding.ivDialogInfo.tentaCarregar(
            "${BASE_URL_IMAGES}${args.movieView.banner ?: args.movieView.cartaz}"
        )
        binding.btnDialogInfoOk.setOnClickListener {
            this.dismiss()
        }
    }
}
