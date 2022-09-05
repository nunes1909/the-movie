package com.gabriel.themovie.util.state

sealed class ResourceState<T>(val data: T? = null, val message: String? = null) {
    class Succes<T>(data: T) : ResourceState<T>(data)
    class Error<T>(data: T? = null, message: String? = null) : ResourceState<T>(data, message)
    class Loading<T> : ResourceState<T>()
    class Empty<T> : ResourceState<T>()
}
