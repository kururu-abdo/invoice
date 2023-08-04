package com.eamar.invoice.src.features.auth.domain.repository

import com.eamar.invoice.src.features.auth.data.model.User
import kotlinx.coroutines.flow.Flow

interface    RemoteUserRepository {


  suspend  fun signUser(user:User): Flow<User>
    suspend fun loginUser(email:String,  password:String)
     fun fetchUserProfile(id:String): Flow<User>
    suspend  fun updateUser(user: User)
    suspend fun deleteUser(id:String)


}