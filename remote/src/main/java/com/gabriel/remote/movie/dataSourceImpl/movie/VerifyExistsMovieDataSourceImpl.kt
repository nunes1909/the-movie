package com.gabriel.remote.movie.dataSourceImpl.movie

import com.gabriel.data.movie.dataSource.movie.VerifyExistsMovieDataSource
import com.gabriel.remote.util.constants.ConstantsRemote.COLECAO_FAV
import com.gabriel.remote.util.constants.ConstantsRemote.MOVIE_ID
import com.gabriel.remote.util.constants.ConstantsRemote.USUARIO_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "VerifyExists"
private const val MSG = "VerifyExistsMovieDataSourceImpl/verifyExistsMovie"

class VerifyExistsMovieDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : VerifyExistsMovieDataSource {
    override suspend fun verifyExistsMovie(id: Int) = channelFlow {
        val usuarioId = firebaseAuth.currentUser?.uid
        val colecao = firestore.collection(COLECAO_FAV)

        colecao
            .whereEqualTo(USUARIO_ID, usuarioId)
            .whereEqualTo(MOVIE_ID, id)
            .get()
            .addOnSuccessListener { documents ->
                CoroutineScope(IO).launch {
                    if (documents.isEmpty) {
                        this@channelFlow.trySend(true)
                    } else {
                        this@channelFlow.trySend(false)
                    }
                }
            }

        colecao.get().addOnFailureListener { exception ->
            Timber.tag(TAG).e(exception, MSG)
        }
    }
}
