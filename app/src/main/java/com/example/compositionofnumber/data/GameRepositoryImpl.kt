package com.example.compositionofnumber.data

import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import com.example.compositionofnumber.domain.entities.Question
import com.example.compositionofnumber.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl: GameRepository {
    
    override fun generateQuestion(maxSum: Int, countOfAnswers: Int): Question {
        val sum = Random.nextInt(from = 2, until = maxSum + 1)
        val visibleNumber =  Random.nextInt(from = 1, until = sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val startRange = max(rightAnswer- countOfAnswers , 1)
        val endRange = min(maxSum , rightAnswer + countOfAnswers)
        while (options.size < countOfAnswers) {
            options.add(Random.nextInt(from = startRange, until = endRange))
        }
        return Question(sum,visibleNumber, options = options.toList())
    }

    override fun gameSettings(level: Level): GameSettings {
       return  when (level) {
                  Level.TEST -> {
                      GameSettings(10,5,50,60)
                  }
           Level.EASY -> {
               GameSettings(50,6,70,60)
           }
           Level.NORMAL -> {
               GameSettings(100,10,85,60)
           }
           Level.HARD -> {
               GameSettings(1000,10,100,120)
           }

       }
    }
}