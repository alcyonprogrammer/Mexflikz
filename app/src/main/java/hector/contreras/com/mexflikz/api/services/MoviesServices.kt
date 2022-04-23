package hector.contreras.com.mexflikz.api.services

import hector.contreras.com.mexflikz.data.models.MediaListModel
import hector.contreras.com.mexflikz.helpers.THEMOVIEDB_API_KEY
import hector.contreras.com.mexflikz.models.VideoMovieModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {

//    @GET("/3/configuration")
//    fun getConfiguracion(@Query("api_key") api_key:String= API_KEY) : Observable<Configuration>
//
//
//    @GET("/3/movies/popular")
//    fun getPopularMovies(@Query("api_key") api_key:String= API_KEY) : Observable<MediaListModel>
//
//    @GET("/3/tv/popular")
//    fun getPopularTv(@Query("api_key") api_key:String= API_KEY) : Observable<MediaListModel>

    @GET("/3/{type}/{category}")
    fun getListMedia(
        @Path("type") type:String,
        @Path("category") category:String,
        @Query("api_key") api_key:String= THEMOVIEDB_API_KEY,
        @Query("language") language:String= "es ") : Observable<MediaListModel>

    @GET("/3/{type}/{movie_id}/videos")

    fun getVideo(
        @Path("type") type:String,
        @Path("movie_id") movie_id:String,
        @Query("api_key") api_key:String= THEMOVIEDB_API_KEY,
        @Query("language") language:String= "es\n") : Observable<VideoMovieModel>
}