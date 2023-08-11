package com.eamar.invoice.src.features.invoice.data.model

data class Invoice (


    var id: String ,
    var title:String ,
    var userId :String,
    var createdAt: Int ,
    var updatedAt:Int ,

    var status:Int ,
    var items: List<InvoiceItem> = emptyList() ,

        ){
}