package com.gabriel.remote.movie.dataSourceImpl.movie

import com.gabriel.data.movie.dataSource.movie.GetFavMovieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.movie.MovieRemoteMapper
import com.gabriel.remote.movie.model.MovieRemote
import com.gabriel.remote.util.constants.ConstantsRemote.COLECAO_FAV
import com.gabriel.remote.util.constants.ConstantsRemote.USUARIO_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val TAG = "GetFavMovie"
private const val MSG = "GetFavMovieDataSourceImpl/getAllFav"

class GetFavMovieDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val mapper: MovieRemoteMapper
) : GetFavMovieDataSource {
    override suspend fun getAllFav(query: String): ResourceState<List<MovieData>> {
        return suspendCoroutine { continuation ->
            val usuarioId = firebaseAuth.currentUser?.uid
            val colecao = firestore.collection(COLECAO_FAV)

            colecao
                .whereEqualTo(USUARIO_ID, usuarioId)
                .get()
                .addOnSuccessListener { documents ->
                    val listRemote = mutableListOf<MovieRemote>()

                    for (i in documents) {
                        i.toObject(MovieRemote::class.java).also { movieRemote ->
                            listRemote.add(movieRemote)
                        }
                    }

                    val listData = mapper.mapToDataNonNull(listRemote)

                    if (query.isEmpty()) {
                        continuation.resumeWith(Result.success(ResourceState.Success(listData)))
                    } else {
                        val listFilter = listData.filter {
                            it.title.uppercase().contains(query.uppercase())
                        }
                        continuation.resumeWith(Result.success(ResourceState.Success(listFilter)))
                    }
                }

            colecao.get().addOnFailureListener { exception ->
                Timber.tag(TAG).e(exception, MSG)
                continuation.resume(
                    ResourceState.Error(
                        data = listOf(),
                        message = exception.message
                    )
                )
            }
        }
    }
}
