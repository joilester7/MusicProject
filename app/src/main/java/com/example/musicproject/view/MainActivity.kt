package com.example.musicproject.view

import android.graphics.Color.green
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.musicproject.R
import com.example.musicproject.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

val musicArray = arrayOf (
    "Rock",
    "Classic",
    "Pop"
)

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)


        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> updateTabText(0)
                    1 -> updateTabText(1)
                    2 -> updateTabText(2)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.apply {
                        text = musicArray[0]
                        icon = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.rock_icon,
                            null
                        )
                    }
                }
                1 -> {
                    tab.apply {
                        text = musicArray[1]
                        icon = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.classic_icon,
                            null
                        )
                    }
                }
                2 -> {
                    tab.apply {
                        text = musicArray[2]
                        icon = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.pop_iconn,
                            null
                        )
                    }
                }
            }
        }.attach()
    }


    private fun updateTabText(platform: Int) {
        when (platform) {
            0 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.blue, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.blue, null)
                    )
                }
            }
            1 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.blue, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.blue, null)
                    )
                }
            }
            2 -> {
                binding.tabLayout.apply {
                    setTabTextColors(
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.blue, null)
                    )
                    getTabAt(platform)?.icon?.setTint(
                        resources.getColor(R.color.blue, null)
                    )
                }
            }
        }
    }
}