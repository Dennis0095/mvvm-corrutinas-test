package com.example.retotecnico.presentation.sealed


data class GameModel(val title:String, val serialNumber: String, val error: GameError)

sealed class GameError(){
    var code : String? = null

    object RayadoError: GameError()
    object InternetError: GameError()
    object NoError: GameError()
    object CongeladoError: GameError()
    data class VersionError(val version: String): GameError()
}
