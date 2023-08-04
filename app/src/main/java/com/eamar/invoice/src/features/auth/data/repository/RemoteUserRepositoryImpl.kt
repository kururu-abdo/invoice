package com.eamar.invoice.src.features.auth.data.repository

import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton

class RemoteUserRepositoryImpl @Inject constructor(
    private val firebaseAuth:  FirebaseAuth
) : RemoteUserRepository{
    override suspend fun signUser(user: User): Flow<User> {
//        try {
//            var result =
//                firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
//
//            if (result.user != null) {
//                firebaseAuth.currentUser!!
//                    .updateProfile(
//                        UserProfileChangeRequest
//
//                    )
//
//
////          Response.Success<User>(
////              U
////          )
//            } else {
//
//            }
//
//
//        } catch (e: Exception) {
//            Response.Failure(e)
//        }
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(phone: String, password: String) {
        TODO("Not yet implemented")
    }

    override  fun fetchUserProfile(id: String): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }
}