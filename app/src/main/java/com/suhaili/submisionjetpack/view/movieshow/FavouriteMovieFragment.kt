package com.suhaili.submisionjetpack.view.movieshow

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
import com.suhaili.submisionjetpack.adapter.MovieFavPagedListAdapter
import com.suhaili.submisionjetpack.databinding.FragmentMovieBinding
import com.suhaili.submisionjetpack.util.ClickBackMovieItem
import com.suhaili.submisionjetpack.view.DetailMovieActivity
import com.suhaili.submisionjetpack.viewmain.FavouriteMovieViewModel
import com.suhaili.submisionjetpack.viewmain.ViewModelFactory

class FavouriteMovieFragment : Fragment(), ClickBackMovieItem {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private lateinit var mainView: FavouriteMovieViewModel

    private lateinit var adapterPage: MovieFavPagedListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory =
            ViewModelFactory.getInstance(requireActivity())
        mainView =
            ViewModelProvider(requireActivity(), factory)[FavouriteMovieViewModel::class.java]

        adapterPage = MovieFavPagedListAdapter(this)

        with(binding.rvMovie) {
            adapter = adapterPage
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        setLoadingProgress(true)
        mainView.getAllMovieDB().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapterPage.submitList(it)
                binding.progress.visibility = View.GONE
                binding.rvMovie.visibility = View.VISIBLE
                setLoadingProgress(false)
            } else {
                setImageunFavorite()
                binding.progress.visibility = View.VISIBLE
                binding.rvMovie.visibility = View.GONE
            }
        })

    }

    private fun setLoadingProgress(stat: Boolean) {
        if (stat) {
            loadingImage()
            binding.rvMovie.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.rvMovie.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
        }
    }

    private fun loadingImage() {
        Glide.with(this)
            .load(R.drawable.loadinfo)
            .apply(RequestOptions().override(150, 200))
            .into(binding.imageProg)
    }

    private fun setImageunFavorite() {
        Glide.with(this)
            .load(R.drawable.brokenheart)
            .apply(RequestOptions().override(150, 200))
            .into(binding.imageProg)

        Toast.makeText(context, "Nothing Favourite Movie In List :(", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun OnclickItemMovie(iDMovie: String) {
        val move = Intent(context, DetailMovieActivity::class.java)
        move.putExtra(EXTRA_MOVIE, iDMovie)
        startActivity(move)
    }
}