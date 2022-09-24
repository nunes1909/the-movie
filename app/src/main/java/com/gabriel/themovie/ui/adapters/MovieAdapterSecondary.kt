package com.gabriel.themovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gabriel.themovie.databinding.ItemListSecondaryBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.extensions.limitValue
import com.gabriel.themovie.util.extensions.tentaCarregar

class MovieAdapterSecondary : RecyclerView.Adapter<MovieAdapterSecondary.SearchMovieHolder>() {

    inner class SearchMovieHolder(val binding: ItemListSecondaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differConfig = object : DiffUtil.ItemCallback<MovieView>() {
        override fun areItemsTheSame(oldItem: MovieView, newItem: MovieView): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: MovieView, newItem: MovieView): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description &&
                    oldItem.cartaz == newItem.cartaz &&
                    oldItem.banner == newItem.banner &&
                    oldItem.nota == newItem.nota &&
                    oldItem.favorito == newItem.favorito
        }
    }

    private val differ = AsyncListDiffer(this, differConfig)

    var moviesList: List<MovieView>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun getItemCount(): Int = moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieHolder {
        return SearchMovieHolder(
            ItemListSecondaryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchMovieHolder, position: Int) {
        val movie = moviesList[position]
        holder.binding.apply {
            ivItemSeconday.tentaCarregar("${BASE_URL_IMAGES}${movie.cartaz}")
            tvItemTitleSeconday.text = movie.title.limitValue(15, true)
            tvItemDescriptionSeconday.text = movie.description?.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }
    }

    fun setMovieOnClickListener(listener: (MovieView) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemClickListener: ((MovieView) -> Unit)? = null
}