package com.suhaili.submisionjetpack.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.suhaili.submisionjetpack.R
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.databinding.ActivityDetailBinding
import com.suhaili.submisionjetpack.view.movieshow.MovieFragment
import com.suhaili.submisionjetpack.viewmain.DetailViewModel
import com.suhaili.submisionjetpack.viewmain.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mainView: DetailViewModel
    private lateinit var id: String
    private var stateFav = false
    private lateinit var model: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val key = intent.getStringExtra(MovieFragment.EXTRA_MOVIE)
        id = key.toString()
        model = MovieEntity()


        val factory = ViewModelFactory.getInstance(this)
        mainView = ViewModelProvider(
            this, factory
        )[DetailViewModel::class.java]

        mainView.getAllDbMovie().observe(this, {
            if (it != null) {
                for (i in it.indices) {
                    if (id == it[i].idMovie) {
                        stateFav = true
                        model._id = it[i]._id
                        break
                    }
                }
                setFavorit(stateFav)
            }
        })
        mainView.getLoading().observe(this, {
            it.getContentIfNotHandled()?.let {
                if (it) {
                    LoadingProgress(true)
                } else {
                    LoadingProgress(false)
                }
            }
        })
        LoadingProgress(true)
        mainView.getDataMovies(id.toString()).observe(this, {

            supportActionBar?.title = "Detail " + it.originalTitle
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .into(binding.imagepicMovie)
            binding.originalTitle.text = it.originalTitle

            val data = it.genres
            val strb = StringBuilder()
            for (i in 0 until data!!.size) {
                if (i == data.size) {
                    strb.append(data[i].name)
                } else {
                    strb.append(data[i].name + ",")
                }
            }
            binding.genreDetailmovie.text = strb.toString()
            binding.releaseMovie.text = it.releaseDate
            binding.overview.text = it.overview
            binding.tagline.text = it.tagline
            binding.homepage.text = it.homepage
            binding.rate.text = it.voteAverage.toString()
            val data2 = it.productionCompanies
            val strb2 = StringBuilder()
            for (i in 0 until data2!!.size) {
                if (i == data2.size) {
                    strb2.append(data2[i].name)
                } else {
                    strb2.append(data2[i].name + ",")
                }
            }
            binding.idProduction.text = strb2.toString()
            binding.popularity.text = it.popularity.toString()
            binding.status.text = it.status

            model.idMovie = it.id
            model.posterPath = it.posterPath
            model.rating = it.voteAverage.toString()
            model.release = it.releaseDate
            model.originalTitle = it.originalTitle
        })

        if (savedInstanceState != null) {
            val load = savedInstanceState.getBoolean("load")
            LoadingProgress(load)
        }

        binding.favoriteMovie.setOnClickListener {
            if (stateFav) {
                mainView.delFavourite(model)
                stateFav = false
                setFavorit(false)
                Snackbar.make(
                    binding.root,
                    "Delete To Favourite List Succesfully!",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {
                mainView.addFavourite(model)
                stateFav = true
                setFavorit(true)
                Snackbar.make(
                    binding.root,
                    "Add To Favourite List Succesfully!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("load", false)
    }

    private fun setGif() {
        Glide.with(this)
            .load(this.resources.getDrawable(R.drawable.loadinfo))
            .apply(RequestOptions().override(150, 150))
            .into(binding.animatedProg)
    }

    private fun LoadingProgress(stat: Boolean) {
        if (stat) {
            setGif()
            binding.loadingprog.visibility = View.VISIBLE
            binding.primary.visibility = View.GONE
        } else {
            binding.loadingprog.visibility = View.GONE
            binding.primary.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else -> return false
        }
        return false
    }

    fun setFavorit(stat: Boolean) {
        if (stat) {
            binding.favoriteMovie.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favoriteMovie.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}