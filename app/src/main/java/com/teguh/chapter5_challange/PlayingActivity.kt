package com.teguh.chapter5_challange

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.teguh.chapter5_challange.databinding.ActivityPlayingBinding
import com.teguh.chapter5_challange.src.HandFace
import com.teguh.chapter5_challange.src.Player

class PlayingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlayingBinding

    private lateinit var result: String
    private var player1 : Player? = null
    private var player2 : Player? = null
    private lateinit var listOfComHand : List<ImageView>
    private lateinit var listOfUserHand : List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listOfComHand = listOf(binding.imgComPaper, binding.imgComRock, binding.imgComScissors)
        listOfUserHand = listOf(binding.imgUserPaper, binding.imgUserRock, binding.imgUserScissors)

        player1 = intent.getParcelableExtra<Player>(getString(R.string.parcelPlayer))
        player2 = Player(intent.getStringExtra("opponent"),null)
        binding.tvMyname.text = player1?.nama.toString()
        binding.tvOpponentname.text = player2?.nama.toString()

        putAllListener()
        startPlay()
    }

    fun startPlay() {
        for (img in listOf(binding.imgUserPaper, binding.imgUserRock, binding.imgUserScissors, binding.imgComScissors, binding.imgComRock, binding.imgComPaper)){
            img.setBackgroundColor(Color.TRANSPARENT)
        }
        player1?.handFace = null
        player2?.handFace = null
        unblockPlayer1Choice()
        blockPlayer2Choice()
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
            startPlay()
        }
        binding.imgClose.setOnClickListener {
            returnToMain()
        }
    }

    private fun toggleOpponentHand(imgOpponent : ImageView) {
        imgOpponent.setBackgroundResource((R.drawable.rounded_corner_frame))
        for (hand in listOfComHand) {
            if (hand.tag != player2?.handFace) {
                hand.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        blockPlayer2Choice()
        Toast.makeText(this, "${player2?.nama} memilih ${player2?.handFace}", Toast.LENGTH_SHORT).show()
        getWinner()
    }

    private fun getWinner() {
        result = HandFace.winOrLose(player1?.handFace!!, player2?.handFace!!)
        Toast.makeText(this, "Result = $result", Toast.LENGTH_SHORT).show()
        //WinnerDialogFragment().show(supportFragmentManager,"winnerFragment")
        var winner = ""
        var status = "MENANG!"
        if (result == HandFace.WIN){
            winner = player1?.nama!!
        } else if (result == HandFace.LOSE){
            winner = player2?.nama!!
        } else {
            winner = ""
            status = "SERI!"
        }
        WinnerDialogFragment.newInstance(winner, status).show(supportFragmentManager,"winnerFragment")
    }

    private fun toggleUserHand(imgUser : ImageView) {
        imgUser.setBackgroundResource(R.drawable.rounded_corner_frame)
        for (hand in listOfUserHand) {
            if (hand.tag != player1?.handFace) {
                hand.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        Toast.makeText(this, "${player1?.nama} memilih ${player1?.handFace}", Toast.LENGTH_SHORT).show()
        blockPlayer1Choice()
        getOpponentInput()
    }

    private fun getOpponentInput() {
        if (player2?.nama == getString(R.string.human_user)){
            unblockPlayer2Choice()
        } else{
            player2?.handFace = HandFace.generateRandomChoice()
            for (hand in listOfComHand){
                if (hand.tag == player2?.handFace){
                    toggleOpponentHand(hand)
                }
            }
        }
    }

    private fun blockPlayer1Choice(){
        for (hand in listOfUserHand){
            hand.isClickable = false
        }
    }

    private fun unblockPlayer1Choice(){
        for (hand in listOfUserHand){
            hand.isClickable = true
        }
    }

    private fun blockPlayer2Choice(){
        for (hand in listOfComHand){
            hand.isClickable = false
        }
    }

    private fun unblockPlayer2Choice(){
        for (hand in listOfComHand){
            hand.isClickable = true
        }
    }

    fun returnToMain(){
        val gameModePage = Intent(this, GameModeActivity::class.java)
        gameModePage.putExtra("PlayerData", player1)
        startActivity(gameModePage)
    }
}