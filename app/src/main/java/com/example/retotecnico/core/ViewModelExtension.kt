package com.example.retotecnico.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.RequestResult
import com.example.retotecnico.BuildConfig
import kotlinx.coroutines.*

typealias DoTask<T> = suspend CoroutineScope.() -> RequestResult<T>
typealias SuccessResult<T> = (success: T) -> Unit
typealias ErrorResult = ((Exception) -> Unit)?
typealias Finally = (() -> Unit)?


fun <T> ViewModel.launchOnIO(
    doTask: DoTask<T>,
    result: SuccessResult<T>,
    error: ErrorResult = null,
    finally: Finally = null
): Job {
    return viewModelScope.launch {
        try {
            withContext(Dispatchers.IO) {
                doTask()
            }.also {
                when (it) {
                    is RequestResult.Success -> result(it.value)
                    is RequestResult.Error -> error?.invoke(it.exception)
                }
            }
        } catch (ex: Exception) {
            if (BuildConfig.DEBUG) {
                println("Ocurrió un error")
                ex.printStackTrace()
            }
            error?.invoke(ex)
        } finally {
            finally?.invoke()
        }
    }
}