package com.teguh.chapter5_challange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.load
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.img_internet).load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
        val secondPage = Intent(this, SplashActivity::class.java)
        Timer().schedule(5000){
            startActivity(secondPage)
        }
    }
}