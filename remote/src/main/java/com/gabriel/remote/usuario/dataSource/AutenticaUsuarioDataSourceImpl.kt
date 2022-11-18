package com.gabriel.remote.usuario.dataSource

import com.gabriel.data.usuario.dataSource.AutenticaUsuarioDataSource
import com.gabriel.data.usuario.model.UsuarioData
import com.gabriel.domain.util.state.ResourceState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AutenticaUsuarioDataSourceImpl(private val firebaseAuth: FirebaseAuth) :
    AutenticaUsuarioDataSource {
    override suspend fun autenticaUsuario(usuario: UsuarioData): ResourceState<Boolean> {
        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(usuario.email!!, usuario.senha!!)
                .addOnSuccessListener {
                    continuation.resume(ResourceState.Default(data = true))
                }
                .addOnFailureListener { exception ->
                    val message = catchErrorAuth(exception)
                    continuation.resume(ResourceState.Default(data = false, message = message))
                }
        }
    }

    override suspend fun autenticaGoogle(tokenId: String): ResourceState<Boolean> {
        return suspendCoroutine { continuation ->
            val credential = GoogleAuthProvider.getCredential(tokenId, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener {
                    continuation.resume(ResourceState.Default(true))
                }
                .addOnFailureListener { exception ->
                    val message = catchErrorAuth(exception)
                    continuation.resume(ResourceState.Default(data = false, message = message))
                }
        }
    }

    private fun catchErrorAuth(exception: Exception?): String = when (exception) {
        is FirebaseAuthInvalidUserException,
        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha incorretos"
        else -> "Erro desconhecido -> ${exception?.message}"
    }
}
