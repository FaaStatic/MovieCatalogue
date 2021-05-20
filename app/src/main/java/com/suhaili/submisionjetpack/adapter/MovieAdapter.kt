package com.suhaili.submisionjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suhaili.submisionjetpack.data.model.MovieGetDataModel
import com.suhaili.submisionjetpack.databinding.ItemmovieBinding
import com.suhaili.submisionjetpack.util.ClickBackMovieItem

class MovieAdapter(val clickBack: ClickBackMovieItem) :
    RecyclerView.Adapter<MovieAdapter.Itemtarget>() {
    private val listItem = ArrayList<MovieGetDataModel>()

    fun setListMovie(value: ArrayList<MovieGetDataModel>) {
        if (value == null) return
        this.listItem.clear()
        this.listItem.addAll(value)
        notifyDataSetChanged()
    }

    inner class Itemtarget(private val binding: ItemmovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: MovieGetDataModel) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${value.posterPath}")
                .apply(RequestOptions().override(100, 160))
                .apply(RequestOptions().transform(RoundedCorners(10)))
                .into(binding.moviePic)
            val yearPick = value.releaseDate!!.split("-")
            binding.title.text = value.originalTitle
            binding.genreDetailmovie.text = yearPick[0]
            binding.ratingDetail.text = value.voteAverage.toString()
            binding.cvMovie.setOnClickListener {
                clickBack.OnclickItemMovie(value.id.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Itemtarget {
        return Itemtarget(
            ItemmovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Itemtarget, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size


}