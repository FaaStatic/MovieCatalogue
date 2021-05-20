package com.suhaili.submisionjetpack.view.movieshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.suhaili.submisionjetpack.R
import com.suhaili.submisionjetpack.adapter.MovieAdapter
import com.suhaili.submisionjetpack.databinding.FragmentMovieBinding
import com.suhaili.submisionjetpack.util.ClickBackMovieItem
import com.suhaili.submisionjetpack.view.DetailMovieActivity
import com.suhaili.submisionjetpack.viewmain.MovieViewModel
import com.suhaili.submisionjetpack.viewmain.ViewModelFactory


class MovieFragment : Fragment(), ClickBackMovieItem {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private lateinit var mainView: MovieViewModel
    private lateinit var AdapterMovie: MovieAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            mainView = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]
            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            AdapterMovie = MovieAdapter(this)
            binding.rvMovie.adapter = AdapterMovie
            binding.rvMovie.setHasFixedSize(true)
            progressLoad(true)
            mainView.getMovieShow().observe(viewLifecycleOwner, {
                progressLoad(true)
                AdapterMovie.setListMovie(it)
                AdapterMovie.notifyDataSetChanged()
                progressLoad(false)
            })
        }
    }

    private fun progressLoad(stat: Boolean) {
        if (stat) {
            setImageGif()
            binding.rvMovie.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.rvMovie.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
        }
    }

    fun setImageGif() {
        Glide.with(requireActivity())
            .load(requireActivity().resources.getDrawable(R.drawable.loadmovie))
            .into(binding.imageProg)
    }


    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        val TAG = MovieFragment::class.java.simpleName
    }


    override fun OnclickItemMovie(iDMovie: String) {
        val move = Intent(context, DetailMovieActivity::class.java)
        move.putExtra(EXTRA_MOVIE, iDMovie)
        startActivity(move)
    }
}