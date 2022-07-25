package com.example.musicproject.view

import android.content.Intent
import android.graphics.Color.green
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.musicproject.R
import com.example.musicproject.data.AlbumResponse
import com.example.musicproject.data.RandomAlbum
import com.example.musicproject.databinding.AlbumListItemBinding
import com.example.musicproject.databinding.RockListBinding
import com.example.musicproject.network.ApiService
import com.example.musicproject.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RockFragment(private val apiService: ApiService,
                   private val term: String, /*private val intent: Intent*/): Fragment() {

    lateinit var binding: RockListBinding

    var albumResponse = AlbumResponse(results = listOf(RandomAlbum(
            artistName = "Not found",
            collectionName = "Not found",
            artworkUrl60 = "Not found",
            trackPrice = 0.0
        ))
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RockListBinding.inflate(layoutInflater)

        fetchRandomAlbums()
        updateBackground()

        return binding.root

    }

    private fun fetchRandomAlbums() {
        apiService.getRandomMusic("roc", "music", "song", "50")?.enqueue(object : Callback<AlbumResponse>{
                override fun onResponse(
                    call: Call<AlbumResponse>,
                    response: Response<AlbumResponse>
                ) {
                    if(response.isSuccessful) {
                        albumResponse = response.body()!!
                        val adapter = RockAdapter(albumResponse.results)
                        binding.rvRockList.adapter = adapter
                    }

//                    binding.tvArtistName.text = albumResponse.results?.get(0)?.artistName
//                    binding.tvAlbumName.text = albumResponse.results?.get(0)?.collectionName
//                    binding.tvTrackPrice.text = albumResponse.results?.get(0)?.trackPrice.toString()



//                    Glide.with(binding.ivAlbumPhoto)
//                        .load(albumResponse.results?.get(0)?.artworkUrl60)
//                        .into(binding.ivAlbumPhoto)
                }

                override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun updateBackground() {
        when (term) {
            "roc" -> binding.root.rootView.setBackgroundColor(
                resources.getColor(
                    R.color.red,
                    null
                )
            )
            "classic" -> binding.root.rootView.setBackgroundColor(
                resources.getColor(
                    R.color.blue,
                    null
                )
            )
            "po" -> binding.root.rootView.setBackgroundColor(
                resources.getColor(
                    R.color.pink,
                    null
                )
            )
        }
    }
}