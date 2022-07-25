package com.example.musicproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicproject.R
import com.example.musicproject.data.RandomAlbum
import com.example.musicproject.databinding.AlbumListItemBinding

class PopAdapter(private val list: List<RandomAlbum>):
    RecyclerView.Adapter<PopAdapter.PopViewHolder>() {

    inner class PopViewHolder(private val binding: AlbumListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(randomAlbum: RandomAlbum) {
            binding.tvArtistName.text = randomAlbum.artistName
            binding.tvAlbumName.text = randomAlbum.collectionName
            binding.tvTrackPrice.text = randomAlbum.trackPrice.toString()

            // Image View
            Glide.with(binding.ivAlbumPhoto)
                .load(randomAlbum.artworkUrl60)
                .placeholder(R.drawable.down_arrow)
                .into(binding.ivAlbumPhoto)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopAdapter.PopViewHolder {
        return PopViewHolder(
            AlbumListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopViewHolder, position: Int) {
        holder.onBind(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }


}