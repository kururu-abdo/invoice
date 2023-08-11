package com.eamar.invoice.src.features.invoice.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eamar.invoice.src.core.Response
import com.eamar.invoice.src.features.invoice.data.model.Invoice
import com.eamar.invoice.src.features.invoice.data.model.InvoiceItem
import com.eamar.invoice.src.features.invoice.data.model.NewInvoice
import com.eamar.invoice.src.features.invoice.domain.usecases.InvoiceUseCases

import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InvoiceViewModel
    @Inject constructor

    (var invoiceUseCases: InvoiceUseCases  ) : ViewModel(){

var newInvoiceState: MutableState<NewInvoiceState> =

mutableStateOf(
    NewInvoiceState(

    )
)



    var newDocumentState: MutableState<DocumentReference?> =
        mutableStateOf(
null
    )
    var invoiceName: MutableState<String> = mutableStateOf("")
    var invoiceNameValid: MutableState<Boolean> = mutableStateOf(false)
    var invoiceNameErrMsg: MutableState<String> = mutableStateOf("")


    var itemName: MutableState<String> = mutableStateOf("")
    var itemNameValid: MutableState<Boolean> = mutableStateOf(false)
    var itemNameErrMsg: MutableState<String> = mutableStateOf("")

    var itemQuantity: MutableState<Int> = mutableStateOf(0)
    var itemQuantityValid: MutableState<Boolean> = mutableStateOf(false)
    var itemQuantityErrMsg: MutableState<String> = mutableStateOf("")

    var itemPrice: MutableState<Double> = mutableStateOf(0.0)
    var itemPriceValid: MutableState<Boolean> = mutableStateOf(false)
    var itemPriceErrMsg: MutableState<String> = mutableStateOf("")


    var isEnabledAddInvoceBtn: MutableState<Boolean> = mutableStateOf(false)

    var isEnabledAddItemBtn: MutableState<Boolean> = mutableStateOf(false)



    init {

}

    private fun shouldEnableAddInvoiceButton() {
//        isEnabledRegisterButton.value = userNameErrMsg.value.isEmpty()
//                &&
        isEnabledAddInvoceBtn.value=
            invoiceName.value.isNotEmpty()
                    && invoiceNameErrMsg.value.isEmpty()

    }
    private fun shouldEnabledAddItemButton() {
//        isEnabledRegisterButton.value = userNameErrMsg.value.isEmpty()
//                &&
        isEnabledAddItemBtn.value=
            itemName.value.isNotEmpty()
                    && itemNameErrMsg.value.isEmpty()
                    &&
                    (itemQuantity.value != null || itemQuantity.value!=0  )
                && itemQuantityErrMsg.value.isEmpty()
                    &&
                    (itemPrice.value != null || itemPrice.value!=0.0  )
                    && itemPriceErrMsg.value.isEmpty()
    }





    fun validateInvoiceName() {
        if (invoiceName.value.isEmpty()) {
            invoiceNameValid.value = false
            invoiceNameErrMsg.value = "Invoice Title is Required"
        } else {
            invoiceNameValid.value = true
            invoiceNameErrMsg.value = ""
        }
        shouldEnableAddInvoiceButton()
    }
    fun valideteItemName() {
        if (itemName.value.isEmpty()) {
            itemNameValid.value = false
            itemNameErrMsg.value = "Item Title is Required"
        } else {
            itemNameValid.value = true
            itemNameErrMsg.value = ""
        }
        shouldEnabledAddItemButton()
    }

    fun validateItemPrice() {
        if (itemPrice.value!= null  &&  itemPrice.value >0   ) {
            itemPriceValid.value = false
            itemPriceErrMsg.value = "Enter Valid Price"
        } else {
            itemNameValid.value = true
            itemNameErrMsg.value = ""
        }
        shouldEnabledAddItemButton()
    }

    fun validateItemQuantity() {
        if (itemQuantity.value!= null  &&  itemQuantity.value >0   ) {
            itemQuantityValid.value = false
            itemQuantityErrMsg.value = "Enter Valid Quantity"
        } else {
            itemQuantityValid.value = true
            itemQuantityErrMsg.value = ""
        }
        shouldEnabledAddItemButton()
    }

fun  addInvoice() = viewModelScope.launch{

    newInvoiceState.value =   newInvoiceState.value.copy(
        isLoading = true
    )
    val currentDateTime: java.util.Date = java.util.Date()
    val currentTimestamp: Long = currentDateTime.time
var newInvoice = NewInvoice(

    title = invoiceName.value ,
    items = emptyList() ,
    createdAt = currentTimestamp,
    updatedAt = currentTimestamp,

    status = 0 ,
    userId = ""
)

    invoiceUseCases.addNewInvoiceUseCase(
        newInvoice
    ).collect{
        if (it is Response.Success){


            newDocumentState.value =



                it.data
            newInvoiceState.value =   newInvoiceState.value.copy(
                isLoading = false ,
                isError = false
            )
        }else{
            newInvoiceState.value =   newInvoiceState.value.copy(
                isLoading = false ,
                isError = true ,
                errorMsg = (it as Response.Failure).e!!.message.toString()
            )
        }
    }

    newInvoiceState.value =   newInvoiceState.value.copy(
        isLoading = false ,
        isError = false
    )

}
}

data class NewInvoiceState(
    var newInvoice: NewInvoice?=null  ,
    var items: List<InvoiceItem> = emptyList() ,
    var isLoading:Boolean = false ,
    var isError:Boolean =false ,
    var errorMsg :String =""
)