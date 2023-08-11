package com.eamar.invoice.src.di

import com.eamar.invoice.src.features.auth.data.repository.RemoteUserRepositoryImpl
import com.eamar.invoice.src.features.auth.domain.repository.IsLoggedInResponse
import com.eamar.invoice.src.features.auth.domain.repository.RemoteUserRepository
import com.eamar.invoice.src.features.auth.domain.usecase.*
import com.eamar.invoice.src.features.invoice.data.repository.RemoteInvoiceRepositoryImpl
import com.eamar.invoice.src.features.invoice.domain.repository.RemoteInvoiceRepository
import com.eamar.invoice.src.features.invoice.domain.usecases.AddNewInvoiceUseCase
import com.eamar.invoice.src.features.invoice.domain.usecases.InvoiceUseCases
import com.eamar.invoice.src.utils.Constants
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideInvoiceRef() = Firebase.firestore.collection(Constants.INVOICE_COLLECTION)
    @Provides
    fun provideFirebaseAuth() =  Firebase.auth
    @Provides
    fun provideFireStore() =  Firebase.firestore
    @Provides
    fun provideUserRepository(
        firebaseAuth: FirebaseAuth
    ): RemoteUserRepository = RemoteUserRepositoryImpl(firebaseAuth)

    @Provides
    fun provideUseCases(
        repo: RemoteUserRepository
    ) = AuthUseCases(
        loginUSer = LoginUserUseCase(repo),
        signUser = SignUserUseCase(repo),
        deleteUser = DeleteUserUseCase(repo) ,
        updateUser = UpdateUserDataUseCase(repo) ,
        logoutUser = SignOutUseCase(repo) ,
        isLggedInUseCase = CheckIfLoggedInUseCase(repo),
        fetchUser = GetUserDataUseCase(repo)
    )


    @Provides
    fun provideInvoiceRepository(
        firestore: FirebaseFirestore ,
        auth: FirebaseAuth
    ): RemoteInvoiceRepository =
        RemoteInvoiceRepositoryImpl(firestore , auth)
    @Provides
    fun provideInvoiceUseCases(
        repo: RemoteInvoiceRepository
    ) = InvoiceUseCases(
        addNewInvoiceUseCase = AddNewInvoiceUseCase(repo),

    )
}