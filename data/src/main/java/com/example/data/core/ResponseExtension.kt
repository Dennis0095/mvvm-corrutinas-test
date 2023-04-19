package com.example.data.core

import android.util.Log
import com.example.domain.core.RequestResult
import retrofit2.Response

fun <T, R> Response<T>?.validateResponse(
    transform: T.() -> R,
): RequestResult<R> {
    try {
        this?.let { response ->
            val errorBody: String? = response.errorBody()?.string()
            if (response.isSuccessful && errorBody.isNullOrEmpty()) {
                val responseBody: T? = response.body()
                return responseBody?.let { data ->
                    RequestResult.Success(transform.invoke(data))
                } ?: kotlin.run {
                    Log.i(
                        "BaseResponse ",
                        "responseBody:: Error con codigo diferente de 200 "
                    )
                    RequestResult.Error(BaseException.GeneralException())
                }
            } else {
                Log.i("BaseResponse ", "responseBody:: ERROR 1")
                if (code() == 401) {
                    return RequestResult.Error(BaseException.GeneralException(code().toString()))
                }
                Log.i("BaseResponse ", "responseBody:: responseBody   code()" + code())
                return RequestResult.Error(BaseException.GeneralException("Ocurrio algún problema , contactarse con el personal"))
            }
        } ?: kotlin.run {
            Log.i("BaseResponse ", "responseBody:: ERROR 2")
            return RequestResult.Error(BaseException.GeneralException())
        }
    } catch (ex: Exception) {
        Log.i("BaseResponse ", "responseBody:: ERROR 3")
        return RequestResult.Error(BaseException.GeneralException())
    }
}

