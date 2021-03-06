package com.teguh.chapter5_challange.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.teguh.chapter5_challange.GameModeActivity
import com.teguh.chapter5_challange.R
import com.teguh.chapter5_challange.databinding.ActivitySplashBinding
import com.teguh.chapter5_challange.src.Player

class SplashActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator : DotsIndicator
    private lateinit var imgNext : ImageView
    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager2
        dotsIndicator = binding.dotsIndicator
        imgNext = binding.imgNext

        val pagerAdapter = SplashAdapter(this)
        viewPager.adapter = pagerAdapter
        dotsIndicator.setViewPager2(viewPager)

        pagerAdapter.addFragment(BasicFragment.newInstance(R.drawable.ic_landing_page1,"Bermain suit melawan sesama\npemain"))
        pagerAdapter.addFragment(BasicFragment.newInstance(R.drawable.ic_landing_page2,"Bermain suit melawan\nkomputer"))
        pagerAdapter.addFragment(NameInputFragment.newInstance(R.drawable.ic_landing_page3,"Tuliskan namamu dibawah"))

        imgNext.setOnClickListener {
            if (viewPager.currentItem == pagerAdapter.itemCount-1){
                val et = findViewById<EditText>(R.id.et_nameinput)
                if (et.text.isNotEmpty()){
                    val gameModePage = Intent(this, GameModeActivity::class.java)
                    gameModePage.putExtra("PlayerData", Player(et.text.toString(),null))
                    startActivity(gameModePage)
                } else {
                    Toast.makeText(this, "Please fill your name", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            }
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}