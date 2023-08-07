package com.eamar.invoice.src.features.auth.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eamar.invoice.R
import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.auth.domain.repository.LoginUserRespone
import com.eamar.invoice.src.features.auth.domain.repository.SignUserRespone
import com.eamar.invoice.src.features.auth.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel

    @Inject constructor(
        private  val   useCases: AuthUseCases

    )

    : ViewModel() {

    var authState: MutableState<AuthState> =
        mutableStateOf(
            AuthState(
            user = null
        )
        )


    var errMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var userName: MutableState<String> = mutableStateOf("")
    var userNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrMsg: MutableState<String> = mutableStateOf("")


    var isLoggedIn: MutableState<Boolean> = mutableStateOf(false)




    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confPasswordErrMsg: MutableState<String> = mutableStateOf("")

    var passwordVisibilty: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordVisibilty: MutableState<Boolean> = mutableStateOf(false)


    var isEnabledRegisterButton: MutableState<Boolean> = mutableStateOf(false)
    var isEnabledLoginButton: MutableState<Boolean> = mutableStateOf(false)


    var loginRespone by mutableStateOf<Response<User>>(Response.Loading)
        private set
    var signUserResponse by mutableStateOf<Response<User>>(Response.Loading)
        private set

    init {
        errMsg.value=""
checkIfLoggedIn()
    }
    fun checkIfLoggedIn() =viewModelScope.launch {

    useCases.isLggedInUseCase().collect{
        if (it is Response.Success) {
            isLoggedIn.value = (it as Response.Success<Boolean>).data
        }
        else {
            isLoggedIn.value = false
        }

    }
    }
    fun loginUser() = viewModelScope.launch {
        errMsg.value =""

        loginRespone = Response.Loading

        authState.value=   authState.value.copy(
            isLoading = true
        )
        useCases.logoutUser()

         useCases.loginUSer(email.value  , password.value).collect{
             loginRespone= it
             if (loginRespone is Response.Success<User>){
                 authState.value=        authState.value.copy(
                     user =
                     (loginRespone as Response.Success<User>)
                         .data,
                     isLoading = false
                 )
             }else {
                 errMsg.value  = (loginRespone as Response.Failure).e.toString()

                 authState.value=       authState.value.copy(
                     user =
                     null,
                     isLoading = false
                 )
             }

        }
    }

    fun signUser() = viewModelScope.launch {
        signUserResponse = Response.Loading


        authState


            .value.copy(
            isLoading = true
        )
     var newUser=   User(id = "" , userName =
     userName.value
         , email = email.value ,password = password.value ,
            userIcon = R.drawable.ic_user
            )


        authState.value=    authState.value.copy(
            isLoading = true
        )
        useCases.signUser(newUser).collect{
            signUserResponse= it
if (signUserResponse is Response.Success<User>){
    authState.value=  authState.value.copy(
        user =
        (signUserResponse as Response.Success<User>)
            .data,
        isLoading = false
    )
}else {
    errMsg.value  = (signUserResponse as Response.Failure).e.toString()

    authState.value=  authState.value.copy(
        user =
        null,
        isLoading = false
    )
}



        }

    }


    private fun shouldEnabledRegisterButton() {
//        isEnabledRegisterButton.value = userNameErrMsg.value.isEmpty()
//                &&
        isEnabledRegisterButton.value=
                emailErrMsg.value.isEmpty()
                && passwordErrMsg.value.isEmpty()
                && confPasswordErrMsg.value.isEmpty()
                && userName.value.isNotEmpty()
                        &&
                        userNameErrMsg.value.isEmpty()
                && email.value.isNotEmpty()
                && password.value.isNotEmpty()
                && confirmPassword.value.isNotEmpty()

        isEnabledLoginButton
            .value =
            emailErrMsg.value.isEmpty()
                    && passwordErrMsg.value.isEmpty()&&
            email.value.isNotEmpty() &&
                    password.value.isNotEmpty()
    }

    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }
    fun validateUserName() {


        if (userName.value.isEmpty()||userName.value.isBlank()  ) {

                userNameValid.value = false
                userNameErrMsg.value = "User name is required"
            }






        else {
            userNameValid.value = false
            userNameErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }
    fun validatePassword() {
        if (password.value.isEmpty()||password.value.isBlank()  ) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password is Required"
        }
       else  if (password.value.length<6) {
            isPasswordValid.value = true
            passwordErrMsg.value = "Password should be at least 6 characters"
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if (confirmPassword.value.isEmpty()||confirmPassword.value.isBlank()  ) {
            isConfirmPasswordValid.value = true
            confPasswordErrMsg.value = "Password is Required"
        }

        else  if (confirmPassword.value!=password.value) {
            isConfirmPasswordValid.value = true
            confPasswordErrMsg.value = "passwords not matche"
        }
        else  if (confirmPassword.value.length<6) {
            isConfirmPasswordValid.value = true
            confPasswordErrMsg.value = "Password should be at least 6 characters"
        }
        else {
            isConfirmPasswordValid.value = false
            confPasswordErrMsg.value = ""
        }
        shouldEnabledRegisterButton()
    }





}

data class AuthState(
    var user: User?=null ,
var isLoading :Boolean= false
)
