package com.teguh.chapter5_challange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.teguh.chapter5_challange.src.Player

class GameModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_mode)
        val player = intent.getParcelableExtra<Player>("PlayerData")
        findViewById<TextView>(R.id.tv_gmode_player).text = "${player?.nama} VS Pemain"
        findViewById<TextView>(R.id.tv_gmode_cpu).text = "${player?.nama} VS CPU"
    }
}