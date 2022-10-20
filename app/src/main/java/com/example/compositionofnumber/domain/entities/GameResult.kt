package com.example.compositionofnumber.domain.entities

import java.io.Serializable

data class GameResult  (
    val winOrNot: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
        )  : Serializable