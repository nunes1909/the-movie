package com.gabriel.remote.usuario.dataSource

import com.gabriel.data.usuario.dataSource.CadastraUsuarioDataSource
import com.gabriel.data.usuario.model.UsuarioData
import com.gabriel.domain.util.state.ResourceState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CadastraUsuarioDataSourceImpl(private val firebaseAuth: FirebaseAuth) :
    CadastraUsuarioDataSource {
    override suspend fun cadastraUsuario(usuario: UsuarioData): ResourceState<Boolean> {
        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(usuario.email!!, usuario.senha!!)
                .addOnSuccessListener {
                    continuation.resume(ResourceState.Default(data = true))
                }
                .addOnFailureListener { exception ->
                    val message = catchErrorCadastro(exception)
                    continuation.resume(ResourceState.Default(data = false, message = message))
                }
        }
    }

    private fun catchErrorCadastro(exception: Exception?): String = when (exception) {
        is FirebaseAuthWeakPasswordException -> "A senha precisa ter pelo menos 6 dígitos."
        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha inválido."
        is FirebaseAuthUserCollisionException -> "E-mail já cadastrado."
        else -> "Erro desconhecido -> ${exception?.message}"
    }
}
