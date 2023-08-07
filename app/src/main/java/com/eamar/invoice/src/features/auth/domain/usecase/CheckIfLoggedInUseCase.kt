package com.eamar.invoice.src.features.auth.domain.usecase

import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository

class CheckIfLoggedInUseCase(var userRepository: RemoteUserRepository){

    suspend operator  fun invoke ()=
        userRepository.isloggedIn()
}