package com.suhaili.submisionjetpack.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.databinding.ItemmovieBinding
import com.suhaili.submisionjetpack.util.ClickBackMovieItem

class MovieFavPagedListAdapter(private val clickBack: ClickBackMovieItem) :
    PagedListAdapter<MovieEntity, MovieFavPagedListAdapter.TargetItem>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieEntity> =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.originalTitle == newItem.originalTitle && oldItem.idMovie == newItem.idMovie
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    inner class TargetItem(private val binding: ItemmovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: MovieEntity) {
            binding.title.text = value.originalTitle
            binding.ratingDetail.text = value.rating
            binding.genreDetailmovie.text = value.release
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${value.posterPath}")
                .apply(RequestOptions().override(100, 160))
                .into(binding.moviePic)

            binding.cvMovie.setOnClickListener {
                clickBack.OnclickItemMovie(value.idMovie.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetItem {
        return TargetItem(
            ItemmovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TargetItem, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
    }
}