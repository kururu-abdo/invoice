package com.eamar.invoice.src.features.invoice.domain.repository

import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.auth.data.model.User
import com.eamar.invoice.src.features.invoice.data.model.Invoice
import com.eamar.invoice.src.features.invoice.data.model.InvoiceItem
import com.eamar.invoice.src.features.invoice.data.model.NewInvoice
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow



typealias AddInvoiceResponse = Response<DocumentReference>
typealias AddInvoiceItemResponse = Response<Boolean>


interface RemoteInvoiceRepository {

    fun fetchUserInvoices(id:String): Flow<List<Invoice>>

    suspend fun addInvoice(newInvoice: NewInvoice ) :
            Flow<AddInvoiceResponse>



    suspend fun addInvoiceItem(invoiceItem: InvoiceItem , invoiceId:String) :Flow<AddInvoiceResponse>
    suspend fun updateInvoice(invoice: Invoice)
    suspend fun updateInvoiceItem(
        invoceId: String  ,
        invoiceItem: InvoiceItem
    )
    suspend fun deleteInvoiceItem(
        invoceId: String  ,
        invoiceItem: InvoiceItem
    )
    suspend fun deleteInvoice(
        invoceId: String  ,
    )


// print invoice


    //generete pdf for invoice




}