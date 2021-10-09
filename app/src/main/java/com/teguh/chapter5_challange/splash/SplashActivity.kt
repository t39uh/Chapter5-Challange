package com.teguh.chapter5_challange.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.teguh.chapter5_challange.R

class SplashActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewPager = findViewById(R.id.view_pager2)
        val pagerAdapter = SplashAdapter(this)

        viewPager.adapter = pagerAdapter

        pagerAdapter.addFragment(BasicFragment.newInstance(R.drawable.ic_landing_page1,"Bermain suit melawan sesama\npemain"))
        pagerAdapter.addFragment(BasicFragment.newInstance(R.drawable.ic_landing_page2,"Bermain suit melawan \nkomputer"))
        pagerAdapter.addFragment(NameInputFragment.newInstance(R.drawable.ic_landing_page3,"Tuliskan namamu dibawah"))
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}