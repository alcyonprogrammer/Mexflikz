package hector.contreras.com.mexflikz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hector.contreras.com.mexflikz.R
import hector.contreras.com.mexflikz.data.models.MediaInfo
import hector.contreras.com.mexflikz.databinding.FragmentListVideoBinding
import hector.contreras.com.mexflikz.helpers.*
import hector.contreras.com.mexflikz.ui.detail.DetailActivity

class ListVideoFragment(val type: String, val category: String) : Fragment() {
    constructor() : this("", "")
    lateinit var info:MediaInfo
    lateinit var binding: FragmentListVideoBinding
    private val viewmodel: ListVideoViewModel by viewModels {
        ListVideoViewModelFactory(
            type,
            category
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_video, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.sectionTitle.text = when(category){
            CATEGORY_POPULAR->"Popular"
            CATEGORY_TOP->"Top Rated"
            CATEGORY_LATEST->"Lastest"
            CATEGORY_NOW_PLAYING ->"Now Playing"
            else->"Upcoming"
        }
        setObservables()
        return binding.root
    }

    private fun setObservables() {
        viewmodel.listVideosItems.observe(viewLifecycleOwner) { listMedia ->
            binding.container.isVisible = true
            binding.loader.isVisible=false
            binding.listVideo.adapter =
                VideoAdapter(listMedia.results,R.layout.video_item,Poster.getImageSizeByCategory(category)) {
                    if (it is MediaInfo) {

                        info = it
                        viewmodel.getListVideos(info.id.toString())
                    }
                }
        }

        viewmodel.listVideosItem.observe(viewLifecycleOwner) { listVideo ->

            val detailIntent = Intent(requireContext(), DetailActivity::class.java)
            detailIntent.putExtra("info",info)
            detailIntent.putExtra("videos",listVideo)
            startActivity(detailIntent)

        }
    }

}
