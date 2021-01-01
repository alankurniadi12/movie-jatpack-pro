package com.alankurniadi.submission2jatpackpromovie.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alankurniadi.submission2jatpackpromovie.api.RetrofitConfig
import com.alankurniadi.submission2jatpackpromovie.api.Url
import com.alankurniadi.submission2jatpackpromovie.api.Url.BASE_URL
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowAiringTvResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowPlayingResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.ResponseResultTrending
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.TrendingResponse
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper(private val context: Context) {

    fun loadTrendingWeek(): MutableLiveData<List<TrendingResponse>> {
        val list = MutableLiveData<List<TrendingResponse>>()
        val call = RetrofitConfig().getInitInstance()
        call.getTrendingWeek().enqueue(object : Callback<ResponseResultTrending> {
            override fun onResponse(
                call: Call<ResponseResultTrending>,
                response: Response<ResponseResultTrending>
            ) {
                val body = response.body()?.results
                list.postValue(body)
            }

            override fun onFailure(call: Call<ResponseResultTrending>, t: Throwable) {
                Log.e("WeekViewModel", t.toString())
            }
        })
        return list
    }

    fun loadNowPlayingMovie(): MutableLiveData<ArrayList<NowPlayingResponse>> {
        val listMovie = MutableLiveData<ArrayList<NowPlayingResponse>>()
        val data = ArrayList<NowPlayingResponse>()
        val url = "${BASE_URL}movie/now_playing?api_key=${Url.API_KEY}"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    val responseObjects = JSONObject(result)
                    val responseArray = responseObjects.getJSONArray("results")
                    for (i in 0 until responseArray.length()) {
                        val dataObjects = responseArray.getJSONObject(i)
                        val id = dataObjects.getInt("id")
                        val backDrop = dataObjects.getString("backdrop_path")
                        val overview = dataObjects.getString("overview")
                        val poster = dataObjects.getString("poster_path")
                        val date = dataObjects.getString("release_date")
                        val title = dataObjects.getString("original_title")
                        val vote = dataObjects.getDouble("vote_average")
                        val mNowPlaying = NowPlayingResponse(id, backDrop, overview, poster, date, title, vote)
                        data.add(mNowPlaying)
                    }
                    listMovie.postValue(data)
                }catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.e("PlayingMovie onFailur", error.message.toString())
            }
        })
        return listMovie
    }

    fun loadTvShowToDay(): MutableLiveData<ArrayList<NowAiringTvResponse>> {
        val listTv = MutableLiveData<ArrayList<NowAiringTvResponse>>()
        val data = ArrayList<NowAiringTvResponse>()
        val url = "${BASE_URL}tv/airing_today?api_key=${Url.API_KEY}"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String(it) }
                try {
                    val responseObjects = JSONObject(result)
                    val responseArray = responseObjects.getJSONArray("results")
                    for (i in 0 until responseArray.length()) {
                        val dataObjects = responseArray.getJSONObject(i)
                        val id = dataObjects.getInt("id")
                        val backDrop = dataObjects.getString("backdrop_path")
                        val date = dataObjects.getString("first_air_date")
                        val name = dataObjects.getString("name")
                        val overView = dataObjects.getString("overview")
                        val poster = dataObjects.getString("poster_path")
                        val vote = dataObjects.getDouble("vote_average")
                        val mTv = NowAiringTvResponse(id, backDrop,date, name, overView, poster, vote)
                        data.add(mTv)
                    }
                    listTv.postValue(data)
                }catch (e: Exception) {
                    Log.e("Exception", e.message.toString())
                }
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.e("TvAiring onFailur", error.message.toString())
            }
        })
        return listTv
    }
}
