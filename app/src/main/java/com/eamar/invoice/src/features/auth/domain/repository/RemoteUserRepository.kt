package com.eamar.invoice.src.features.auth.domain.repository

import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.auth.data.model.User
import kotlinx.coroutines.flow.Flow

typealias UpdateUserRespone = Response<Boolean>
typealias SignUserRespone = Response<User>
typealias LoginUserRespone = Response<User>
typealias DeleteUserRespone = Response<Boolean>
typealias IsLoggedInResponse = Response<Boolean>
interface    RemoteUserRepository {


    fun signUser(user:User): Flow<Response<User>>
     fun loginUser(email:String,  password:String): Flow<Response<User>>

     fun fetchUserProfile(id:String): Flow<User>
    suspend  fun updateUser(user: User):UpdateUserRespone
    suspend fun deleteUser(id:String):DeleteUserRespone
    suspend fun logout()
    suspend fun isloggedIn():Flow<IsLoggedInResponse>

}