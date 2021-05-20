package com.suhaili.submisionjetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.suhaili.submisionjetpack.data.model.GetTVDataModel
import com.suhaili.submisionjetpack.databinding.ItemtvBinding
import com.suhaili.submisionjetpack.util.ClickBackTVItem

class TVAdapter(private val clickBack: ClickBackTVItem) :
    RecyclerView.Adapter<TVAdapter.ItemTarget>() {
    private val listItem = ArrayList<GetTVDataModel>()

    fun setListTV(value: ArrayList<GetTVDataModel>) {
        if (value == null) return
        this.listItem.clear()
        this.listItem.addAll(value)
        notifyDataSetChanged()
    }

    inner class ItemTarget(private val binding: ItemtvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: GetTVDataModel) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${value.posterPath}")
                .apply(RequestOptions().override(100, 160))
                .apply(RequestOptions().transform(RoundedCorners(10)))
                .into(binding.tvpic)
            val yearRelease = value.firstAirDate!!.split("-")
            binding.title.text = value.originalName
            binding.rate.text = value.voteAverage.toString()
            binding.episode.text =
                value.numberOfEpisodes + " Episode" + "/" + value.numberOfSeasons + " Season"
            binding.releaseMovie.text = yearRelease[0]


            binding.cvTV.setOnClickListener {
                clickBack.onClickItemTV(value.id.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTarget {
        return ItemTarget(ItemtvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemTarget, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size


}