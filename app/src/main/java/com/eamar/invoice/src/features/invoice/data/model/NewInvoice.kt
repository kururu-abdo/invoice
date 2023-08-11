package com.eamar.invoice.src.features.invoice.data.model

import com.eamar.invoice.src.features.invoice.data.model.InvoiceItem

data class NewInvoice (


    var userId: String,
    var title:String,
    var createdAt: Long,
    var updatedAt:Long,

    var status:Int,
    var items: List<InvoiceItem> = emptyList(),

    ){
}