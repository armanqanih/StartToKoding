package org.lotka.xenonx.domain.util


interface FileUploadCallbacks {
    fun onProgressUpdate(percentage: Int)
    fun onError()
    fun onFinish()
}