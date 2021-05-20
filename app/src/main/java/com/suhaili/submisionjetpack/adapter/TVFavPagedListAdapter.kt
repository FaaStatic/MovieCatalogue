package com.suhaili.submisionjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.databinding.ItemtvBinding
import com.suhaili.submisionjetpack.util.ClickBackTVItem

class TVFavPagedListAdapter(private val clickBack: ClickBackTVItem) :
    PagedListAdapter<TVEntity, TVFavPagedListAdapter.TargetItem>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TVEntity> =
            object : DiffUtil.ItemCallback<TVEntity>() {
                override fun areItemsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                    return oldItem.idTV == newItem.idTV && oldItem.originalTitle == newItem.originalTitle
                }

                override fun areContentsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                    return oldItem == newItem
                }

            }
    }


    inner class TargetItem(private val binding: ItemtvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun Bind(value: TVEntity) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${value.posterPath}")
                .apply(RequestOptions().override(100, 160))
                .apply(RequestOptions().transform(RoundedCorners(10)))
                .into(binding.tvpic)
            val yearRelease = value.firsAir?.split("-")
            binding.title.text = value.originalTitle
            binding.releaseMovie.text = yearRelease?.get(0)
            binding.rate.text = value.rating
            binding.episode.text =
                "Episode ${value.episode}" + "/" + "${value.season}"
            binding.cvTV.setOnClickListener {
                clickBack.onClickItemTV(value.idTV.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetItem {
        return TargetItem(ItemtvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TargetItem, position: Int) {
        holder.Bind(getItem(position) as TVEntity)
    }


}