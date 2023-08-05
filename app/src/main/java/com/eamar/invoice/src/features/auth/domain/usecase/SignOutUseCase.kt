package com.eamar.invoice.src.features.auth.domain.usecase

import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository

class SignOutUseCase(var userRepository: RemoteUserRepository) {

    suspend  operator   fun  invoke() =  userRepository.logout()
}