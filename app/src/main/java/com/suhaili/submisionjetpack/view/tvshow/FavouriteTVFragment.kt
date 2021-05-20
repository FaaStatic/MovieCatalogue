package com.suhaili.submisionjetpack.view.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suhaili.submisionjetpack.R
import com.suhaili.submisionjetpack.adapter.TVFavPagedListAdapter
import com.suhaili.submisionjetpack.databinding.FragmentTVBinding
import com.suhaili.submisionjetpack.util.ClickBackTVItem
import com.suhaili.submisionjetpack.view.DetailTVActivity
import com.suhaili.submisionjetpack.viewmain.FavouriteTVViewModel
import com.suhaili.submisionjetpack.viewmain.ViewModelFactory

class FavouriteTVFragment : Fragment(), ClickBackTVItem {

    private lateinit var binding: FragmentTVBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTVBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private lateinit var mainView: FavouriteTVViewModel
    private lateinit var Adapter: TVFavPagedListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory =
            ViewModelFactory.getInstance(requireActivity())
        mainView = ViewModelProvider(this, factory)[FavouriteTVViewModel::class.java]
        Adapter = TVFavPagedListAdapter(this)
        binding.rvTv.adapter = Adapter
        binding.rvTv.setHasFixedSize(true)
        binding.rvTv.layoutManager = LinearLayoutManager(context)

        setLoadingProgress(true)
        mainView.getAllTVDBPaging().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                Adapter.submitList(it)
                binding.rvTv.visibility = View.VISIBLE
                binding.progressTV.visibility = View.GONE
                setLoadingProgress(false)
            } else {
                setImageunFavorite()
                binding.rvTv.visibility = View.GONE
                binding.progressTV.visibility = View.VISIBLE
            }
        })
    }


    companion object {
        const val EXTRA_TV = "extra_tv"

    }

    private fun setLoadingProgress(stat: Boolean) {
        if (stat) {
            loadingImage()
            binding.rvTv.visibility = View.GONE
            binding.progressTV.visibility = View.VISIBLE
        } else {
            binding.rvTv.visibility = View.VISIBLE
            binding.progressTV.visibility = View.GONE
        }
    }

    private fun loadingImage() {
        Glide.with(this)
            .load(R.drawable.loadinfo)
            .apply(RequestOptions().override(150, 200))
            .into(binding.imageProgTV)
    }

    private fun setImageunFavorite() {
        Glide.with(this)
            .load(R.drawable.brokenheart)
            .apply(RequestOptions().override(150, 200))
            .into(binding.imageProgTV)

        Toast.makeText(context, "Nothing Favourite TV In List :(", Toast.LENGTH_SHORT).show()
    }

    override fun onClickItemTV(idTv: String) {
        val move = Intent(context, DetailTVActivity::class.java)
        move.putExtra(EXTRA_TV, idTv)
        startActivity(move)
    }


}