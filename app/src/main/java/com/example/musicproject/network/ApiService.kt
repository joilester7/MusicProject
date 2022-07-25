package com.example.musicproject.network

import android.util.Log
import com.example.musicproject.data.AlbumResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    fun getRandomMusic(
        @Query("term") term: String,
        @Query("media") media: String,
        @Query("entity") entity: String,
        @Query("limit") limit: String
    ): Call<AlbumResponse>


    companion object {
        // ^ how we achieve static in Kotlin
        private var retrofit: Retrofit? = null

        // full url for rock
        // https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50

        // full url for classic
        // https://itunes.apple.com/search?term=classick&amp;media=music&amp;entity=song&amp;limit=50

        // full url for pop
        // https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50

        //Singleton
        fun getRetrofitInstance(): ApiService {
            Log.d("***","Retrofit Before: $retrofit")
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/search/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            Log.d("***", "Retrofit After: $retrofit")
            return retrofit!!.create(ApiService::class.java)
        }
    }
}