package com.suhaili.submisionjetpack.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suhaili.submisionjetpack.view.movieshow.MovieFragment
import com.suhaili.submisionjetpack.view.tvshow.TVFragment

class PagerAdapter(ctx: FragmentActivity) : FragmentStateAdapter(ctx) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieFragment()
            1 -> return TVFragment()
            else -> return Fragment()
        }
    }
}