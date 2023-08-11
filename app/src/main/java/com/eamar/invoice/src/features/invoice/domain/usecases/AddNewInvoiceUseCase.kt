package com.eamar.invoice.src.features.invoice.domain.usecases

import com.eamar.invoice.src.features.invoice.data.model.Invoice
import com.eamar.invoice.src.features.invoice.data.model.NewInvoice
import com.eamar.invoice.src.features.invoice.domain.repository.RemoteInvoiceRepository

class AddNewInvoiceUseCase(
   var  remoteInvoiceRepository: RemoteInvoiceRepository
) {

    suspend operator fun invoke(newInvoice: NewInvoice)=
        remoteInvoiceRepository.addInvoice(newInvoice)

}