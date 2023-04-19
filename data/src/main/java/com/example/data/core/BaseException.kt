package com.example.data.core

import com.example.data.core.ConstantCore.Error.Message.GENERAL_ERROR_MESSAGE
import java.io.IOException

sealed class BaseException (errorMessage: String)
    : IOException(errorMessage){

    data class GeneralException(
        var errorMessage: String = GENERAL_ERROR_MESSAGE,
    ) : BaseException(errorMessage)

}