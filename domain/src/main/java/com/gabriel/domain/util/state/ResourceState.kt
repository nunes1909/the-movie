package com.gabriel.domain.util.state

sealed class ResourceState<T>(val data: T? = null, cod: Int? = null, val message: String? = null) {
    class Succes<T>(data: T) : ResourceState<T>(data)
    class Undefined<T>(data: T? = null, cod: Int? = null, message: String? = null) : ResourceState<T>(data, cod, message)
    class Error<T>(data: T? = null, cod: Int? = null, message: String? = null) : ResourceState<T>(data, cod, message)
    class Loading<T> : ResourceState<T>()
    class Empty<T> : ResourceState<T>()
}
