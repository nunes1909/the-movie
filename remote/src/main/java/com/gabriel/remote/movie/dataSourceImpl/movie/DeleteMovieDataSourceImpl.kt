package com.gabriel.remote.movie.dataSourceImpl.movie

import android.util.Log
import com.gabriel.data.movie.dataSource.movie.DeleteMovieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.remote.movie.mapper.movie.MovieRemoteMapper
import com.gabriel.remote.util.constants.ConstantsRemote
import com.gabriel.remote.util.constants.ConstantsRemote.COLECAO_FAV
import com.gabriel.remote.util.constants.ConstantsRemote.MOVIE_ID
import com.gabriel.remote.util.constants.ConstantsRemote.USUARIO_ID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

private const val TAG = "DeleteMovie"
private const val MSG = "DeleteMovieDataSourceImpl/delete"

class DeleteMovieDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val mapper: MovieRemoteMapper
) : DeleteMovieDataSource {
    override fun delete(entity: MovieData) {
        val usuarioId = firebaseAuth.currentUser?.uid
        val movieRemote = mapper.mapToRemote(entity)
        val colecao = firestore.collection(COLECAO_FAV)

        colecao
            .whereEqualTo(USUARIO_ID, usuarioId)
            .whereEqualTo(MOVIE_ID, movieRemote.id)
            .get()
            .addOnSuccessListener { documents ->
                for (doc in documents) {
                    colecao.document(doc.id).delete()
                }
            }
            .addOnFailureListener { exception ->
                Timber.tag(TAG).e(exception, MSG)
            }
    }
}
