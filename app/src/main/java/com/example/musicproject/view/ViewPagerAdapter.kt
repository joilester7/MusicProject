package com.example.musicproject.view

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicproject.network.ApiService


private const val NUM_TABS = 3


public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {

    private val apiService = ApiService.getRetrofitInstance()

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return RockFragment(apiService, "roc")
            1 -> return ClassicFragment(apiService, "classic")
        }
        return PopFragment(apiService, "po")

        //PagerSnapHelper().attachToRecyclerView()
    }

//    fun startIntent(url: String): Intent {
//        val webpage = Uri.parse(url)
//        val intent = Intent(Intent.ACTION_VIEW, webpage)
//        if (intent.resolveActivity(getPackageManager()) != null) {
//
//        }
//    }
}