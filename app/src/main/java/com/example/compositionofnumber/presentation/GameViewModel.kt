package com.example.compositionofnumber.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.compositionofnumber.R
import com.example.compositionofnumber.data.GameRepositoryImpl
import com.example.compositionofnumber.domain.entities.GameResult
import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import com.example.compositionofnumber.domain.entities.Question
import com.example.compositionofnumber.domain.usecases.GenerateQuestionUseCase
import com.example.compositionofnumber.domain.usecases.GetGameSettingsUseCase

class GameViewModel(application: Application): AndroidViewModel(application) {

    private  var context = application

    private val repository =  GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)


    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level

    //LiveData
    private val _formattedTime = MutableLiveData<String>()
    val formattedTime : LiveData<String>
        get() = _formattedTime

    private val _questions = MutableLiveData<Question>()
    val questions : LiveData<Question>
    get() = _questions

    private val _percentageOfRightAnswers =  MutableLiveData<Int>()
    val percentageOfRightAnswers : LiveData<Int>
    get() = _percentageOfRightAnswers

    private val _progressAnswers =  MutableLiveData<String>()
    val progressAnswers : LiveData<String>
        get() = _progressAnswers

    private val _enoughCountOfAnswers =  MutableLiveData<Boolean>()
    val enoughCountOfAnswers : LiveData<Boolean>
        get() = _enoughCountOfAnswers

    private val _enoughPercentageOfAnswers =  MutableLiveData<Boolean>()
    val enoughPercentageOfAnswers : LiveData<Boolean>
        get() = _enoughPercentageOfAnswers

    private val _minPercent =  MutableLiveData<Int>()
    val minPercent : LiveData<Int>
        get() = _minPercent

    private val _gameResult =  MutableLiveData<GameResult>()
    val gameResult : LiveData<GameResult>
        get() = _gameResult
                                       



    //
    private  var timer : CountDownTimer? = null
    private var countOfRightAnswers = 0
    private var countOfQuestions = 0
    



    fun startGame(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase.invoke(level)
        _minPercent.value = gameSettings.minPercentageofRightAnswers
        startTimer()
        generateQuestion()
        
    }

    private fun generateQuestion() {
                _questions.value =  generateQuestionUseCase(gameSettings.maxSum)
    }

    fun chooseAnswer (number: Int) {
         val rightAnswer = questions.value?.rightAnswer
        if (number == rightAnswer) {
                   countOfRightAnswers++
        }
        countOfQuestions++

        updateProgress()
        generateQuestion()
    }

     private fun  updateProgress(){
         val percentage = ( (countOfRightAnswers/countOfQuestions.toDouble()) * 100  ).toInt()
         _percentageOfRightAnswers.value = percentage
         _progressAnswers.value = String.format(
             context.resources.getString(R.string.progress_answers),
             countOfRightAnswers,
             gameSettings.minCountofRightAnswers
         )
     _enoughCountOfAnswers.value = countOfRightAnswers >= gameSettings.minCountofRightAnswers
     _enoughPercentageOfAnswers.value = percentage >= gameSettings.minPercentageofRightAnswers
     }
    

    private  fun startTimer() {
         timer = object : CountDownTimer(
            gameSettings.gameTimeInSec * 1000L ,1000L
        )  {
            override fun onTick(p0: Long) {
                        val seconds = p0 /1000L
                        val minutes = seconds / 60
                        val leftSeconds = seconds - (minutes * 60)
                _formattedTime.value =  String.format("%02d:%02d", minutes,leftSeconds)
            }
            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private  fun finishGame(){
          val gameResult = GameResult(
              enoughPercentageOfAnswers.value ==  true && enoughCountOfAnswers.value == true,
              countOfRightAnswers,
              countOfQuestions,
              gameSettings
          )
        _gameResult.value = gameResult
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    
}