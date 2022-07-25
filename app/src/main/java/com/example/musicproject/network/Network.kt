package com.example.musicproject.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import com.example.musicproject.data.AlbumResponse
import com.example.musicproject.data.RandomAlbum
import com.example.musicproject.view.RockAdapter
import com.example.musicproject.view.RockFragment
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Network(private val context: Context) {
    private fun checkNetworkState(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo

        info?.let {
            return it.isConnected
        }
        return false
    }

    fun searchAlbumCriteria(term: String): AlbumResponse {
        val baseUrl = "https://itunes.apple.com"
        val endPoint = "search"
        val paramTerm = "term"
        val paramMedia = "media"
        val paramEntity = "entity"
        val paramLimit = "limit"

        val uri = Uri.parse("$baseUrl/$endPoint")
            .buildUpon()
            //.appendQueryParameter(paramQ, "search")
            .appendQueryParameter(paramTerm, "$term")
            .appendQueryParameter(paramMedia, "music")
            .appendQueryParameter(paramEntity, "song")
            .appendQueryParameter(paramLimit, "50")
            .build()

        val requestUrl = URL(uri.toString())
        val httpURLConnection = requestUrl.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = 10000
        httpURLConnection.readTimeout = 15000
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.doInput = true
        httpURLConnection.connect()
        val stringResult = parseInputStream(httpURLConnection.inputStream)

        httpURLConnection.responseCode
        httpURLConnection.responseMessage

        return try {
            parseStringResponse(stringResult)
        } catch (ex: Exception){
            ex.printStackTrace()
            return AlbumResponse(emptyList())
        } finally {
            httpURLConnection.disconnect()
        }

    }


    private fun parseInputStream(inputStream: InputStream): String {
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = reader.readLine()

        while(line != null) {
            builder.append("$line\n")
            line = reader.readLine()
        }

        if (builder.length == 0) {
            return ""
        }
        return builder.toString()

    }

    private fun parseStringResponse(stringResult: String): AlbumResponse {
        val root = JSONObject(stringResult)
        val jsonItems = root.getJSONArray("results")
        val itemsList = ArrayList<RandomAlbum>()

        for(i in 0 until jsonItems.length()) {
            val element = jsonItems.getJSONObject(i)
            val artistName = element.getJSONObject("artistName")
            val collectionName = element.getJSONObject("collectionName")
            val artworkUrl60 = element.getJSONObject("artworkUrl60")
            val trackPrice = element.getJSONObject("trackPrice").getDouble("trackPrice")

            val randomAlbum = RandomAlbum("$artistName","$collectionName","$artworkUrl60",trackPrice  )

            itemsList.add(randomAlbum)
        }
        return AlbumResponse(itemsList)
    }

}