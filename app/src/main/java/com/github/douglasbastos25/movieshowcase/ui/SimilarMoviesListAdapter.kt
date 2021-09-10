package com.github.douglasbastos25.movieshowcase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.douglasbastos25.movieshowcase.data.model.SimilarMovie
import com.github.douglasbastos25.movieshowcase.databinding.ItemSimilarMovieBinding

class SimilarMoviesListAdapter: ListAdapter<SimilarMovie, SimilarMoviesListAdapter.ViewHolder>(DiffCallback()) {

    private var posterUrl: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimilarMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(
        private val binding: ItemSimilarMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimilarMovie){
            binding.tvTitle.text = item.title

            Glide.with(binding.root.context)
                .load(posterUrl + item.poster)
                .into(binding.ivPoster)
        }
    }

    fun setImgUrl(url: String){
        posterUrl = url
    }
}

class DiffCallback: DiffUtil.ItemCallback<SimilarMovie>(){
    override fun areItemsTheSame(oldItem: SimilarMovie, newItem: SimilarMovie) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SimilarMovie, newItem: SimilarMovie) = oldItem == newItem

}
