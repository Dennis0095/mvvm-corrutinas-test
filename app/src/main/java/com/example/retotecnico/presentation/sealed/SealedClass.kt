package com.example.retotecnico.presentation

import com.example.retotecnico.presentation.sealed.GameError
import com.example.retotecnico.presentation.sealed.GameError.*
import com.example.retotecnico.presentation.sealed.GameModel


fun main(){
    var gameList = arrayOf(
        GameModel("mario", "23223", InternetError),
        GameModel("start cra", "23223", RayadoError),
        GameModel("mario 3", "23223", NoError),
        GameModel("mario 3", "23223", VersionError("2.1.1"))
    )


    gameList.forEach {
        when(it.error){
            InternetError -> TODO()
            NoError -> TODO()
            RayadoError -> {
                it.error.code
            }
            CongeladoError -> revisar()
            is VersionError -> setVersionError(it.error.version)
        }
    }

}

fun setVersionError(version: String){

}

fun revisar() {
    TODO("Not yet implemented")
}



