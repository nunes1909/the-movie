package com.gabriel.themovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gabriel.themovie.databinding.ItemPesquisaBinding
import com.gabriel.themovie.model.multiMovie.MultiMovie
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(val binding: ItemPesquisaBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differConfig = object : DiffUtil.ItemCallback<MultiMovie>() {
        override fun areItemsTheSame(oldItem: MultiMovie, newItem: MultiMovie): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: MultiMovie, newItem: MultiMovie): Boolean {
            return  oldItem.id == newItem.id &&
                    oldItem.title == newItem.title
        }
    }

    private val differ = AsyncListDiffer(this, differConfig)

    var movieList: List<MultiMovie>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemPesquisaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.apply {
            itemMovieImage.load("${BASE_URL_IMAGES}${movie.banner}")
            itemMovieTitle.text = movie.title
            cbItemMovieFavorito.isChecked = movie.favorito
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }
    }

    fun setMovieOnClickListener(listener: (MultiMovie) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemClickListener: ((MultiMovie) -> Unit)? = null
}
