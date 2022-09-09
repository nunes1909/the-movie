package com.gabriel.themovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gabriel.themovie.databinding.ItemListBinding
import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.extensions.limitValue

class FilmeAdapter : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    inner class FilmeViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differConfig = object : DiffUtil.ItemCallback<FilmeView>() {
        override fun areItemsTheSame(oldItem: FilmeView, newItem: FilmeView): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: FilmeView, newItem: FilmeView): Boolean {
            return  oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.cartaz == newItem.cartaz &&
                    oldItem.banner == newItem.banner &&
                    oldItem.nota == newItem.nota &&
                    oldItem.favorito == newItem.favorito
        }
    }

    private val differ = AsyncListDiffer(this, differConfig)

    var filmesList: List<FilmeView>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        return FilmeViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = filmesList.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = filmesList[position]
        holder.binding.apply {
            itemListImage.load("${BASE_URL_IMAGES}${filme.cartaz}")
            itemListTitle.text = filme.title.limitValue(10, true)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(filme)
            }
        }
    }

    fun setFilmeOnClickListener(listener: (FilmeView) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemClickListener: ((FilmeView) -> Unit)? = null
}
