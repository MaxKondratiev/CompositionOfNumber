package com.example.compositionofnumber.domain.usecases

import com.example.compositionofnumber.domain.entities.Question
import com.example.compositionofnumber.domain.repository.GameRepository

class GenerateQuestionUseCase(private val gameRepository: GameRepository) {


    operator fun  invoke(maxSum:Int): Question {
      return gameRepository.generateQuestion(maxSum, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}