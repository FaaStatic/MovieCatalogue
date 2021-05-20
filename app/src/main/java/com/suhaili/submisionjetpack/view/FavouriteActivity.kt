package com.suhaili.submisionjetpack.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.suhaili.submisionjetpack.adapter.FavouriteAdapterPager
import com.suhaili.submisionjetpack.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    val FavoriteTittle = arrayOf(
        "Movie Favourite",
        "TV Favourite"
    )

    private lateinit var binding: ActivityFavouriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.pagerFavourite.adapter = FavouriteAdapterPager(this)
        TabLayoutMediator(binding.tabFavourite, binding.pagerFavourite) { tab, position ->
            tab.text = FavoriteTittle[position]
        }.attach()


    }
}