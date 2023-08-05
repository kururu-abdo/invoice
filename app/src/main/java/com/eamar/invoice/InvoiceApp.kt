package com.eamar.invoice

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InvoiceApp : Application() {

//    @Inject lateinit var workerFactory: HiltWorkerFactory

//    HiltWorkerFactoryoverride fun getWorkManagerConfiguration() =
//        Configuration.Builder()
//            .setWorkerFactory(workerFactory)
//            .build()
}