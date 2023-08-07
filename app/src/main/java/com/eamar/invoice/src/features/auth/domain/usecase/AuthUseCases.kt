package com.eamar.invoice.src.features.auth.domain.usecase

data class AuthUseCases (
    val loginUSer : LoginUserUseCase ,
    val deleteUser:DeleteUserUseCase ,
    val signUser : SignUserUseCase ,
    val logoutUser : SignOutUseCase ,
    val updateUser : UpdateUserDataUseCase ,
    val isLggedInUseCase: CheckIfLoggedInUseCase  ,
        )