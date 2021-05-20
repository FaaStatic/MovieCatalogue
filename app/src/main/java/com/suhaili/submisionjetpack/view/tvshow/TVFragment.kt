package com.suhaili.submisionjetpack.view.tvshow

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
import com.suhaili.submisionjetpack.adapter.TVAdapter
import com.suhaili.submisionjetpack.databinding.FragmentTVBinding
import com.suhaili.submisionjetpack.util.ClickBackTVItem
import com.suhaili.submisionjetpack.view.DetailTVActivity
import com.suhaili.submisionjetpack.viewmain.TVViewModel
import com.suhaili.submisionjetpack.viewmain.ViewModelFactory


class TVFragment : Fragment(), ClickBackTVItem {

    private lateinit var binding: FragmentTVBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTVBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private lateinit var mainView: TVViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            mainView = ViewModelProvider(
                this,
                factory
            )[TVViewModel::class.java]
            val adapter = TVAdapter(this)
            binding.rvTv.adapter = adapter
            binding.rvTv.layoutManager = LinearLayoutManager(context)
            binding.rvTv.setHasFixedSize(true)
            progressLoad(true)
            mainView.getTvShow()!!.observe(viewLifecycleOwner, {
                if (it != null) {
                    progressLoad(true)
                    adapter.setListTV(it)
                    adapter.notifyDataSetChanged()
                    progressLoad(false)
                }
            })
        }
    }

    private fun progressLoad(stat: Boolean) {
        if (stat) {
            setImageGif()
            binding.rvTv.visibility = View.GONE
            binding.progressTV.visibility = View.VISIBLE
        } else {
            binding.rvTv.visibility = View.VISIBLE
            binding.progressTV.visibility = View.GONE
        }
    }

    fun setImageGif() {
        Glide.with(requireActivity())
            .load(requireActivity().resources.getDrawable(R.drawable.loadtv))
            .into(binding.imageProgTV)
    }

    companion object {
        const val EXTRA_TV = "extra_tv"

    }


    override fun onClickItemTV(idTv: String) {
        val move = Intent(context, DetailTVActivity::class.java)
        move.putExtra(EXTRA_TV, idTv)
        startActivity(move)
    }
}