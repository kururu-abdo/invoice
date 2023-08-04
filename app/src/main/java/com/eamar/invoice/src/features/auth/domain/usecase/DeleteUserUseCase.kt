package com.eamar.invoice.src.features.auth.domain.usecase

import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository

class DeleteUserUseCase (var userRepository: RemoteUserRepository){

    suspend operator  fun invoke (id:String)=
        userRepository.deleteUser(id)
}