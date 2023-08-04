package com.eamar.invoice.src.features.auth.domain.usecase

import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository

class UpdateUserDataUseCase (var userRepository: RemoteUserRepository){

    suspend  operator fun  invoke (user: User)=  userRepository.updateUser(user)
}