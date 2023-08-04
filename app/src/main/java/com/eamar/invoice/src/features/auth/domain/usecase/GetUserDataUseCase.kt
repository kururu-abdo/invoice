package com.eamar.invoice.src.features.auth.domain.usecase

import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository

class GetUserDataUseCase (var userRepository: RemoteUserRepository) {

    operator  fun  invoke(id:String)=
         userRepository.fetchUserProfile(id);

}