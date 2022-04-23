package hector.contreras.com.mexflikz.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hector.contreras.com.mexflikz.api.RetrofitManager
import hector.contreras.com.mexflikz.api.services.MoviesServices
import hector.contreras.com.mexflikz.data.models.MediaListModel
import hector.contreras.com.mexflikz.models.VideoMovieModel
import hector.contreras.com.mexflikz.ui.base.BaseViewModel

class ListVideoViewModel (val type: String, val category: String)  : BaseViewModel() {
    var service: MoviesServices = RetrofitManager.getService(MoviesServices::class.java)

    // List of items of Movies, series and readony list observable
    private val _listVideosItems : MutableLiveData<MediaListModel> by lazy {
        MutableLiveData<MediaListModel>().also { getListResources() }
    }
    val listVideosItems: LiveData<MediaListModel> = _listVideosItems


    var listVideosItem = MutableLiveData<VideoMovieModel>()

    private var _onError = MutableLiveData<Boolean>()
    val onError: LiveData<Boolean> = _onError


    private fun getListResources() {
        setupSubscribe(service.getListMedia(type, category),{
            _listVideosItems.value = it
        },{
            _onError.value = true
        })
    }


    fun getListVideos(movie_id: String) {
        setupSubscribe(service.getVideo(type, movie_id),{
            listVideosItem.value = it
        },{
            _onError.value = true
        })
    }


}

class ListVideoViewModelFactory(private val type: String, private val category: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListVideoViewModel(type, category) as T
    }
}
