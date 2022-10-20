package com.example.compositionofnumber.domain.entities

import java.io.Serializable

data class GameSettings (
    val maxSum: Int ,
    val minCountofRightAnswers: Int ,
    val minPercentageofRightAnswers: Int,
    val gameTimeInSec: Int
    
        )  : Serializable