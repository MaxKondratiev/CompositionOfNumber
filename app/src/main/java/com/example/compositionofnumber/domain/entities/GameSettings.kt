package com.example.compositionofnumber.domain.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val maxSum: Int ,
    val minCountofRightAnswers: Int ,
    val minPercentageofRightAnswers: Int,
    val gameTimeInSec: Int
    
        )  : Parcelable