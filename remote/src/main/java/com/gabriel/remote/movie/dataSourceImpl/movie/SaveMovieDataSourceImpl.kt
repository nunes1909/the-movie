package com.gabriel.remote.movie.dataSourceImpl.movie

import com.gabriel.data.movie.dataSource.movie.SaveMovieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.movie.MovieRemoteMapper
import com.gabriel.remote.util.constants.ConstantsRemote.MOVIE_ID
import com.gabriel.remote.util.constants.ConstantsRemote.USUARIO_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val TAG = "SaveMovie"
private const val MSG = "SaveMovieDataSourceImpl/save"

class SaveMovieDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val mapper: MovieRemoteMapper
) : SaveMovieDataSource {
    override suspend fun save(entity: MovieData): ResourceState<Boolean> {
        return suspendCoroutine { continuation ->
            val usuarioId = firebaseAuth.currentUser!!.uid
            val movieRemote = mapper.mapToRemote(entity).apply { this.usuarioId = usuarioId }
            val colecao = firestore.collection("favoritos")

            colecao
                .whereEqualTo(USUARIO_ID, usuarioId)
                .whereEqualTo(MOVIE_ID, movieRemote.id)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        colecao.add(movieRemote)
                        continuation.resume(
                            ResourceState.Success(data = true))
                    }
                }
                .addOnFailureListener { exception ->
                    Timber.tag(TAG).e(exception, MSG)
                    continuation.resume(
                        ResourceState.Error(
                            data = false,
                            message = exception.message
                        )
                    )
                }
        }
    }
}