package hector.contreras.com.mexflikz.ui.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import hector.contreras.com.mexflikz.R
import hector.contreras.com.mexflikz.data.models.MediaInfo
import hector.contreras.com.mexflikz.databinding.ActivityDetailBinding
import hector.contreras.com.mexflikz.helpers.YouTube_API_KEY
import hector.contreras.com.mexflikz.models.VideoMovieModel


class DetailActivity : YouTubeBaseActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        binding.lifecycleOwner = this
        setActionBar(binding.toobarDetail)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
        //Get Arguments

        val vinfo = intent.getParcelableExtra<MediaInfo>("info")
        val listVideo = intent.getParcelableExtra<VideoMovieModel>("videos")

        if (vinfo!=null){
            binding.info = vinfo
            binding.imgSize ="w780"
        }
        setupViews(listVideo)

    }

    private fun setupViews(listVideo: VideoMovieModel?) {
        binding.toobarDetail.setNavigationOnClickListener({finish()})
        initVideoPlayer(listVideo)

    }

    private fun initVideoPlayer(listVideo: VideoMovieModel?) {
        if(listVideo==null || listVideo.results.size == 0){
            binding.youtubeTrailerPlayer.visibility = View.GONE
            return
        }

        val key = listVideo.results.find{it.type.lowercase() == "Trailer".lowercase()}?.key;
        if(key==null){
            binding.youtubeTrailerPlayer.visibility = View.GONE
            return
        }


        var listener = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
                                                 wasRestored: Boolean) {

                binding.imagePreview.visibility = View.GONE
                youTubePlayer?.loadVideo(/*vinfo.name*/key)
                youTubePlayer?.setPlayerStateChangeListener(object: YouTubePlayer.PlayerStateChangeListener {
                    override fun onAdStarted() {
//        Toast.makeText(this, "Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLoading() {
                        youTubePlayer?.pause()
                        View.GONE.also { binding.imagePreview.visibility = it }
                    }

                    override fun onVideoStarted() {
//        Toast.makeText(this, "Video has started", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLoaded(p0: String?) {
                    }

                    override fun onVideoEnded() {
//        Toast.makeText(this, "Congratulations! You've completed another video.", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(p0: YouTubePlayer.ErrorReason?) {
                    }
                })// STOPSHIP:
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
            }
        }

        binding.youtubeTrailerPlayer!!.initialize(YouTube_API_KEY, listener)
    }

}