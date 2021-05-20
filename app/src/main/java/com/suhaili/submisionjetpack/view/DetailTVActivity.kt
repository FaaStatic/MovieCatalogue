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
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.databinding.ActivityDetailTVBinding
import com.suhaili.submisionjetpack.view.tvshow.TVFragment
import com.suhaili.submisionjetpack.viewmain.TVShowViewModel
import com.suhaili.submisionjetpack.viewmain.ViewModelFactory

class DetailTVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTVBinding
    private lateinit var mainView: TVShowViewModel
    private var statFav = false
    private lateinit var modelTV: TVEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTVBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(TVFragment.EXTRA_TV)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val factory = ViewModelFactory.getInstance(this)
        mainView = ViewModelProvider(
            this, factory
        )[TVShowViewModel::class.java]
        modelTV = TVEntity()
        mainView.getAllDbTV().observe(this, {
            if (it != null) {
                for (i in it.indices) {
                    if (id == it[i].idTV) {
                        statFav = true
                        modelTV._id = it[i]._id
                        break
                    }
                }
                setFavorit(statFav)
            }
        })

        mainView.getLoad().observe(this, {
            it.getContentIfNotHandled()?.let {
                if (it) {
                    LoadingProgress(true)
                } else {
                    LoadingProgress(false)
                }
            }
        })

        LoadingProgress(true)
        mainView.getDetailTV(id.toString()).observe(this, {
            supportActionBar?.title = "Detail " + it.originalName
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .into(binding.imagepicMovie)
            binding.originalTitle.text = it.originalName

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
            binding.releaseMovie.text = it.firstAirDate
            binding.lastair.text = it.lastAirDate
            binding.episode.text =
                "${it.numberOfEpisodes} Episodes / ${it.numberOfSeasons} Seasons"
            binding.overview.text = it.overview
            binding.tagline.text = it.tagline
            binding.homepage.text = it.homepage
            binding.rate.text = it.voteAverage.toString()
            val data2 = it.productionCompanies
            val strb2 = StringBuilder()
            for (i in 0 until data2!!.size) {
                if (i == data2.size) {
                    strb2.append(data2[i]?.name)
                } else {
                    strb2.append(data2[i]?.name + ",")
                }
            }
            binding.idProduction.text = strb2.toString()
            binding.popularity.text = it.popularity.toString()
            binding.status.text = it.status
            val data3 = it.networks
            val strb3 = StringBuilder()
            for (i in 0 until data3!!.size) {
                if (i == data3.size) {
                    strb3.append(data3[i]?.name)
                } else {
                    strb3.append(data3[i]?.name + ",")
                }
            }
            binding.network.text = strb3.toString()
            modelTV.idTV = it.id.toString()
            modelTV.originalTitle = it.originalName
            modelTV.episode = it.numberOfEpisodes
            modelTV.season = it.numberOfSeasons
            modelTV.rating = it.voteAverage
            modelTV.firsAir = it.firstAirDate
            modelTV.endAir = it.lastAirDate
            modelTV.posterPath = it.posterPath
        })
        if (savedInstanceState != null) {
            val load = savedInstanceState.getBoolean("load")
            LoadingProgress(load)
        }

        binding.favoriteTV.setOnClickListener {
            if (statFav) {
                mainView.delFavourite(modelTV)
                statFav = false
                setFavorit(false)
                Snackbar.make(
                    binding.root,
                    "Delete To Favourite List Succesfully!",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                mainView.addFavourite(modelTV)
                statFav = true
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
            setGif()
            binding.loadingprog.visibility = View.GONE
            binding.primary.visibility = View.VISIBLE
        }
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
            binding.favoriteTV.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favoriteTV.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }


}