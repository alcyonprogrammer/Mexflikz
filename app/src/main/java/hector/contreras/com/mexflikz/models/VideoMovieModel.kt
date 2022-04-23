package hector.contreras.com.mexflikz.models

import android.os.Parcelable
import hector.contreras.com.mexflikz.data.models.MediaInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoMovieModel (
    val id: Int,
    val results: List<VideoInfo>
    ):Parcelable

@Parcelize
data class VideoInfo (
    val id: String,
    val iso_639_1: String,
    val iso_3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val published_at: String
): Parcelable