package com.example.compositionofnumber.domain.entities

data class GameSettings (
    val maxSum: Int ,
    val minCountofRightAnswers: Int ,
    val minPercentageofRightAnswers: Int,
    val gameTimeInSec: Int
    
        )