package com.eamar.invoice.src.features.auth.domain.usecase

import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository

class LoginUserUseCase(var userRep:RemoteUserRepository) {

    suspend operator fun  invoke(phone:String ,password:String)=

        userRep.loginUser(phone ,password)
}