package com.example.compositionofnumber.domain.usecases

import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import com.example.compositionofnumber.domain.repository.GameRepository

class GetGameSettingsUseCase(private  val gameRepository: GameRepository) {

    operator fun  invoke(level: Level): GameSettings {
        return gameRepository.gameSettings(level)
    }
    
}