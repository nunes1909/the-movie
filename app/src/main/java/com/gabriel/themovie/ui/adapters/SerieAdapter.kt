package com.gabriel.themovie.ui.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.AsyncListDiffer
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.gabriel.themovie.databinding.ItemListBinding
//import com.gabriel.themovie.movie.serie.model.SerieView
//import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
//import com.gabriel.themovie.util.extensions.limitValue
//
//class SerieAdapter : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {
//
//    inner class SerieViewHolder(val binding: ItemListBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//    private val differConfig = object : DiffUtil.ItemCallback<SerieView>() {
//        override fun areItemsTheSame(oldItem: SerieView, newItem: SerieView): Boolean {
//            return oldItem.hashCode() == newItem.hashCode()
//        }
//
//        override fun areContentsTheSame(oldItem: SerieView, newItem: SerieView): Boolean {
//            return  oldItem.id == newItem.id &&
//                    oldItem.title == newItem.title &&
//                    oldItem.description == newItem.description &&
//                    oldItem.banner == newItem.banner &&
//                    oldItem.background == newItem.background &&
//                    oldItem.nota == newItem.nota &&
//                    oldItem.favorito == newItem.favorito
//        }
//    }
//
//    private val differ = AsyncListDiffer(this, differConfig)
//
//    var seriesList: List<SerieView>
//        get() = differ.currentList
//        set(value) = differ.submitList(value)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
//        return SerieViewHolder(
//            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int = seriesList.size
//
//    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
//        val serie = seriesList[position]
//        holder.binding.apply {
//            itemListImage.load("${BASE_URL_IMAGES}${serie.banner}")
//            itemListTitle.text = serie.title.limitValue(10, true)
//        }
//
//        holder.itemView.setOnClickListener {
//            onItemClickListener?.let {
//                it(serie)
//            }
//        }
//    }
//
//    fun setSerieOnClickListener(listener: (SerieView) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    private var onItemClickListener: ((SerieView) -> Unit)? = null
//}
