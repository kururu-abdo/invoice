package com.eamar.invoice.src.features.auth.data.repository

import com.eamar.invoice.R
import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.auth.domain.repository.GetUserRespone
import com.eamar.invoice.src.features.auth.domain.repository.IsLoggedInResponse
import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton

class RemoteUserRepositoryImpl @Inject constructor(
    private val firebaseAuth:  FirebaseAuth
) : RemoteUserRepository{
    override  fun signUser(user: User)  =
        flow {
            try {
                var result =
                    firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()

                if (result.user != null) {
                    firebaseAuth.currentUser!!
                        .updateProfile(

                            userProfileChangeRequest {
                                setDisplayName(user.userName)

                            }

                        )

                    var currentUser = firebaseAuth.currentUser!!


                   emit(
                       Response.Success<User>(
                           User(
                               id = currentUser!!.uid,
                               userName = currentUser.displayName!!,
                               email = currentUser.email!!,
                               userIcon = R.drawable.ic_user,
                               password = ""
                           )
                       )
                   )
                } else {
                    emit(
                        Response.Failure(
                            java.lang.Exception(
                                "No User"
                            )
                        )
                    )
                }


            } catch (e: Exception) {


                emit(
                    Response.Failure(e)
                )
            }
        }




    override  fun loginUser(email: String, password: String)
   =
flow {
    try {
        var result = firebaseAuth.
        signInWithEmailAndPassword(
            email, password
        ).await()
        if (result.user != null) {
            var currentUser = firebaseAuth.currentUser!!

            emit(
                Response.Success<User>(
                    User(
                        id = currentUser!!.uid,
                        userName = currentUser.displayName!!,
                        email = currentUser.email!!,
                        userIcon = R.drawable.ic_user,
                        password = ""
                    )
                )
            )

        } else {
           emit(
               Response.Failure(
                   java.lang.Exception(
                       "Wrong Email/Password"
                   )
               )
           )
        }
    } catch (e: Exception) {
        if (e is FirebaseAuthInvalidUserException){
            emit(    Response.Failure(java.lang.Exception(
                "Wrong Email/Password"
            ))
            )
        }
      emit(
          Response.Failure(e)
      )
    }
}



     override   fun fetchUserProfile(): Flow<GetUserRespone> = flow {
       try {
           var currentUser = firebaseAuth.currentUser

           if (currentUser!= null){

              emit(
                  Response.Success<User>(
                      User(
                          id = currentUser!!.uid,
                          userName = currentUser.displayName!!,
                          email = currentUser.email!!,
                          userIcon = R.drawable.ic_user,
                          password = ""
                      )
                  )
              )
           }else {
              emit(

                  Response.Success<User>(
                      null
//                      java.lang.Exception(
//                          "No User"
//                      )
                  )
              )
           }

       }catch (e:Exception){

          emit( Response.Failure(e))

       }
    }

    override suspend fun updateUser(user: User) =
       try {
           firebaseAuth.currentUser!!
               .updateProfile(

                   userProfileChangeRequest {
                       setDisplayName(user.userName)

                   }

               )
           Response.Success(true)
       }catch (e:Exception){
           Response.Failure(e)
       }


    override suspend fun deleteUser(id: String) =try{
       firebaseAuth.currentUser!!.delete()
        Response.Success(true)
    }catch (e:Exception){
        Response.Failure(e)
    }

    override suspend fun logout() {
       firebaseAuth.signOut()
    }

    override suspend fun isloggedIn(): Flow<IsLoggedInResponse> = flow {
        emit(Response.Success<Boolean>(
            firebaseAuth.currentUser != null
        ))
    }
}