package com.suhaili.submisionjetpack.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.suhaili.submisionjetpack.R
import com.suhaili.submisionjetpack.adapter.PagerAdapter
import com.suhaili.submisionjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        @StringRes
        val TAB_TITLE = arrayOf(
            R.string.TabMovie,
            R.string.TabTV
        )
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabMode, binding.pager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.FavoriteMenu -> {
                val move = Intent(this, FavouriteActivity::class.java)
                startActivity(move)
            }
            else -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

}