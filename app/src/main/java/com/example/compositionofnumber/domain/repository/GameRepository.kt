package com.example.compositionofnumber.domain.repository

import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import com.example.compositionofnumber.domain.entities.Question

interface GameRepository {

    fun generateQuestion(
        maxSum:Int,
        countOfAnswers:Int
    ): Question
    fun gameSettings(level: Level):GameSettings
}