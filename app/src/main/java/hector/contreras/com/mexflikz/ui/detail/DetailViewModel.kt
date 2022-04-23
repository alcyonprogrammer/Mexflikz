package hector.contreras.com.mexflikz.ui.detail

import hector.contreras.com.mexflikz.api.RetrofitManager
import hector.contreras.com.mexflikz.api.services.MoviesServices
import hector.contreras.com.mexflikz.ui.base.BaseViewModel

class DetailViewModel : BaseViewModel() {
    var service: MoviesServices = RetrofitManager.getService(MoviesServices::class.java)
}