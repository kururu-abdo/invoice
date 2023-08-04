package com.eamar.invoice.src.features.invoice.domain.repository

import com.eamar.invoice.src.features.invoice.data.model.Invoice
import com.eamar.invoice.src.features.invoice.data.model.InvoiceItem
import kotlinx.coroutines.flow.Flow

interface RemoteInvoiceRepository {


     fun fetchUserInvoices(id:String): Flow<List<Invoice>>


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