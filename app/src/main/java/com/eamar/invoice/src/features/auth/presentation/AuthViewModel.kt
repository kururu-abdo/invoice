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
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confPasswordErrMsg: MutableState<String> = mutableStateOf("")





    var loginRespone by mutableStateOf<Response<User>>(Response.Loading)
        private set
    var signUserResponse by mutableStateOf<Response<User>>(Response.Loading)
        private set


    fun loginUser(email: String, password: String) = viewModelScope.launch {
        loginRespone = Response.Loading
         useCases.loginUSer(email  , password).collect{
             loginRespone= it

        }
    }

    fun signUser(name:String, email: String, password: String) = viewModelScope.launch {
        signUserResponse = Response.Loading

     var newUser=   User(id = "" , userName = name , email = email ,
        password = password ,
            userIcon = R.drawable.ic_user
            )
        useCases.signUser(newUser).collect{
            signUserResponse= it
        }

    }




    fun validateEmail() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = "Input proper email id"
        } else {
            isEmailValid.value = false
            emailErrMsg.value = ""
        }
//        shouldEnabledRegisterButton()
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
//        shouldEnabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if (password.value != confirmPassword.value) {
            isConfirmPasswordValid.value = true
            confPasswordErrMsg.value = "Password did not match"
        } else {
            isConfirmPasswordValid.value = false
            confPasswordErrMsg.value = ""
        }
//        shouldEnabledRegisterButton()
    }





}


