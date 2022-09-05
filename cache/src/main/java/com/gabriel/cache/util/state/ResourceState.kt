package com.gabriel.cache.util.state

sealed class ResourceState<T>(
    val data: T? = null,
    val cod: Int? = null,
    val message: String? = null
) {
    class Undefined<T>(data: T? = null, cod: Int? = null, message: String? = null) :
        ResourceState<T>(data = data, cod = cod, message = message)
}
