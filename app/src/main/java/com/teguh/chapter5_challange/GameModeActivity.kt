package com.teguh.chapter5_challange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.teguh.chapter5_challange.databinding.ActivityGameModeBinding
import com.teguh.chapter5_challange.src.Player

class GameModeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val player = intent.getParcelableExtra<Player>(getString(R.string.parcelPlayer))
        findViewById<TextView>(R.id.tv_gmode_player).text = "${player?.nama} VS Pemain"
        findViewById<TextView>(R.id.tv_gmode_cpu).text = "${player?.nama} VS CPU"

        val playing = Intent(this, PlayingActivity::class.java)
        playing.putExtra("PlayerData", player)
        binding.imgVsCpu.setOnClickListener {
            playing.putExtra("opponent", getString(R.string.cpu_user))
            startActivity(playing)
        }
        binding.imgVsPlayer.setOnClickListener {
            playing.putExtra("opponent", getString(R.string.human_user))
            startActivity(playing)
        }
    }
}