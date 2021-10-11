package com.teguh.chapter5_challange

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.teguh.chapter5_challange.databinding.ActivityPlayingBinding
import com.teguh.chapter5_challange.src.Player

class PlayingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlayingBinding

    private lateinit var result: String
    private var player1 : Player? = null
    private var player2 : Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player1 = intent.getParcelableExtra<Player>(getString(R.string.parcelPlayer))
        player2 = Player(intent.getStringExtra("opponent"),null)
        binding.tvMyname.text = player1?.nama.toString()
        binding.tvOpponentname.text = player2?.nama.toString()

        putAllListener()
        play()
    }

    private fun play() {
        TODO("Not yet implemented")
    }

    private fun putAllListener() {
        binding.imgUserPaper.setOnClickListener {
            player1?.handFace = getString(R.string.paper)
            toggleUserHand(binding.imgUserPaper)
        }
        binding.imgUserRock.setOnClickListener {
            player1?.handFace = getString(R.string.rock)
            toggleUserHand(binding.imgUserRock)
        }
        binding.imgUserScissors.setOnClickListener {
            player1?.handFace = getString(R.string.scissors)
            toggleUserHand(binding.imgUserScissors)
        }
        binding.imgComPaper.setOnClickListener {
            player2?.handFace = getString(R.string.paper)
            toggleOpponentHand(binding.imgComPaper)
        }
        binding.imgComRock.setOnClickListener {
            player2?.handFace = getString(R.string.rock)
            toggleOpponentHand(binding.imgComRock)
        }
        binding.imgComScissors.setOnClickListener {
            player2?.handFace = getString(R.string.scissors)
            toggleOpponentHand(binding.imgComScissors)
        }
        binding.imgRefresh.setOnClickListener {
            for (img in listOf(binding.imgUserPaper, binding.imgUserRock, binding.imgUserScissors, binding.imgComScissors, binding.imgComRock, binding.imgComPaper)){
                img.setBackgroundColor(Color.TRANSPARENT)
            }
            player1?.handFace = null
            player2?.handFace = null
        }
    }

    private fun toggleOpponentHand(imgOpponent : ImageView) {
        imgOpponent.setBackgroundResource((R.drawable.rounded_corner_frame))
        for (hand in listOf(binding.imgComPaper, binding.imgComRock, binding.imgComScissors)) {
            if (hand.tag != player2?.handFace) {
                hand.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun toggleUserHand(imgUser : ImageView) {
        imgUser.setBackgroundResource(R.drawable.rounded_corner_frame)
        for (hand in listOf(binding.imgUserPaper, binding.imgUserRock, binding.imgUserScissors)) {
            if (hand.tag != player1?.handFace) {
                hand.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun blockPlayer1Choice(){
        for (hand in listOf(binding.imgUserPaper, binding.imgUserRock, binding.imgUserScissors)){
            hand.isClickable = false
        }
    }

    private fun unblockPlayer1Choice(){
        for (hand in listOf(binding.imgUserPaper, binding.imgUserRock, binding.imgUserScissors)){
            hand.isClickable = true
        }
    }

    private fun blockPlayer2Choice(){
        for (hand in listOf(binding.imgComPaper, binding.imgComRock, binding.imgComScissors)){
            hand.isClickable = false
        }
    }

    private fun unblockPlayer2Choice(){
        for (hand in listOf(binding.imgComPaper, binding.imgComRock, binding.imgComScissors)){
            hand.isClickable = true
        }
    }

}