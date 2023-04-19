package com.example.data.core

class ConstantCore {

    object Error {
        object Message {
            const val GENERAL_ERROR_MESSAGE = "Ocurrió un error inesperado"
            const val UNAUTHORIZE_MESSAGE = "Su sesión ha finalizado"
            const val AIRPLANE_ERROR_MESSAGE = "Su sesión ha finalizado"
            const val PUSH_TOKEN_MESSAGE = "Hubo un problema obteniendo el token de notificación"
        }
    }
}