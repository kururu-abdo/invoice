package com.eamar.invoice.src.features.invoice.data.repository

import com.eamar.invoice.R
import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.invoice.data.model.Invoice
import com.eamar.invoice.src.features.invoice.data.model.InvoiceItem
import com.eamar.invoice.src.features.invoice.data.model.NewInvoice
import com.eamar.invoice.src.features.invoice.domain.repository.AddInvoiceItemResponse
import com.eamar.invoice.src.features.invoice.domain.repository.AddInvoiceResponse
import com.eamar.invoice.src.features.invoice.domain.repository.RemoteInvoiceRepository
import com.eamar.invoice.src.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteInvoiceRepositoryImpl
    @Inject constructor(
        private val firebaseFirestore: FirebaseFirestore ,
        private val firebaseAuth:  FirebaseAuth
    )


    : RemoteInvoiceRepository {
    override fun fetchUserInvoices(id: String): Flow<List<Invoice>> {
        TODO("Not yet implemented")
    }

    override suspend fun addInvoice(newInvoice: NewInvoice ): Flow<AddInvoiceResponse>

    = flow{

        try {
            var result =

                firebaseFirestore
                    .collection(
                        Constants.INVOICE_COLLECTION
                    )
                    .add(
                        newInvoice
                            .copy(
                                userId = firebaseAuth.currentUser!!.uid
                            )
                    ).await()




//         var data=   result.snapshots().first()
//            data.

            emit(
               Response.Success(
                   data = result
               )
           )


        } catch (e: Exception) {


            emit(
                Response.Failure(e)
            )
        }

    }

    override suspend fun addInvoiceItem(
        invoiceItem: InvoiceItem,
        invoiceId: String
    ): Flow<AddInvoiceResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun updateInvoice(invoice: Invoice) {
        TODO("Not yet implemented")
    }

    override suspend fun updateInvoiceItem(invoceId: String, invoiceItem: InvoiceItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInvoiceItem(invoceId: String, invoiceItem: InvoiceItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInvoice(invoceId: String) {
        TODO("Not yet implemented")
    }
}