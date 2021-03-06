package com.teguh.chapter5_challange.src

class HandFace{
    companion object{
        const val GUNTING = "SCISSOR"
        const val BATU = "ROCK"
        const val KERTAS = "PAPER"
        const val WIN = "win"
        const val DRAW = "draw"
        const val LOSE = "lose"
        val position = mapOf<String, Int>(GUNTING to 1, BATU to 2, KERTAS to 3)
        fun winOrLose(hand1 : String, hand2: String) : String{
            val count = position[hand1]!! - position[hand2]!!
            if (hand1 == hand2) {
                return DRAW
            } else if ((count == 1) || (count == -2)) {
                return WIN
            }
            return LOSE
        }
        fun generateRandomChoice() : String{
            return listOf( GUNTING, BATU, KERTAS).random()
        }
    }
}