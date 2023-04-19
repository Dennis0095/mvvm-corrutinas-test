package com.example.retotecnico.core

class Constants {

    companion object{
        const val ID_RECIPE = "ID_RECIPE"
        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"
        const val NAME = "NAME"
        const val LINK_ICON = "LINK_ICON"
        const val MIN_TIEMPO_ENTRE_UPDATES = (1000 * 60 * 2).toLong()// 1 minuto
        const val MIN_CAMBIO_DISTANCIA_PARA_UPDATES = 1f // 1.5 metros

    }
}