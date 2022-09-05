package com.gabriel.remote.util.state

sealed class ResourceState<T>(
    val data: T? = null,
    val cod: Int? = null,
    val message: String? = null
) {
    class Undefined<T>(data: T? = null, cod: Int? = null, message: String? = null) :
        ResourceState<T>(data, cod, message)
}
