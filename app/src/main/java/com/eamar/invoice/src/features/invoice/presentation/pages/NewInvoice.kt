package com.eamar.invoice.src.features.invoice.presentation.pages

import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.features.auth.presentation.components.AuthButton
import com.eamar.invoice.src.features.auth.presentation.components.EmailField
import com.eamar.invoice.src.features.invoice.presentation.InvoiceViewModel
import com.eamar.invoice.src.features.invoice.presentation.components.MiniButton
import com.eamar.invoice.src.features.invoice.presentation.components.NormalTextField
import com.eamar.invoice.src.features.invoice.presentation.components.NumberTextField
import com.eamar.invoice.src.utils.Constants
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.checkerframework.common.reflection.qual.NewInstance

@Composable
fun NewInvoice(navController: NavController = rememberNavController()
               ,
             invoiceViewModel: InvoiceViewModel= hiltViewModel()
){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
var enabled  by remember{
    mutableStateOf(false)
}

    var scope = rememberCoroutineScope()
    val dbAnimateAsState: Dp by animateDpAsState(
        targetValue = switch(enabled),
//        animationSpec =
        animationSpec =
//        rememberSplineBasedDecay<Float>()
                tween(durationMillis = 300,
        easing = FastOutSlowInEasing)
    )

    LaunchedEffect(enabled) {


        enabled = true
    }
    var titleState by remember{
        mutableStateOf("New Invoice")
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(





            title = { Text(titleState) },


            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.Filled.ArrowBack ,
                        contentDescription = null

                    )
                }
            },
            backgroundColor =
          Constants.thirdColor ,
            elevation = 0.dp
        )  },


        ){contentPadding ->
        contentPadding.calculateBottomPadding()
        Box( modifier = Modifier

            .fillMaxSize()

            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Constants.thirdColor,
                        Constants.mainColor
                    )
                )
            )
            .padding(
                20.dp
            )


            ,){
//header

    Box(modifier =  Modifier
        .align(
            Alignment.TopCenter
        )

        ,

        contentAlignment = Alignment.Center

    ) {

        if (invoiceViewModel.newDocumentState.value ==null)
            Box (
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxSize()
                    .background(
                        Color.White,

                        shape = RoundedCornerShape(8.dp)
                    )
//        .clip(
//            shape = RoundedCornerShape(8.dp)
//        )
                    .padding(
                        10.dp
                    ) ,
contentAlignment = Alignment.Center
        ){


                Column  (

                    horizontalAlignment = Alignment.CenterHorizontally  ,

                    verticalArrangement = Arrangement.SpaceBetween

                    ) {

                    NormalTextField(
                        value =  invoiceViewModel.invoiceName.value
                        ,
                        onValueChange ={
                            invoiceViewModel.invoiceName.value = it
                            invoiceViewModel.validateInvoiceName()
                        } ,
                        placeholder ="Invoice Title" ,
                        invoiceViewModel = invoiceViewModel
                    )

                    MiniButton(text = "Add" ,

                        onClick = {


scope.launch {
    invoiceViewModel.addInvoice().join()

    if (invoiceViewModel.newDocumentState.value!=null){
        enabled = true
     var data =   invoiceViewModel.newDocumentState.value!!.snapshots().first()
     titleState=  data.data!!["title"]!!.toString()



    }
}


                        },
                        invoiceViewModel = invoiceViewModel
                        ,
                        isLoading = invoiceViewModel.newInvoiceState.value.isLoading ,
                        isEnabled = invoiceViewModel.isEnabledAddInvoceBtn.value
                    )
                }
            }
      else
          Box() {

          }


    }

            //items
            if (invoiceViewModel.newDocumentState.value ==null)
Box(modifier = Modifier.align(Alignment.TopCenter   )) {

}else
                Box() {

                }

            //add items
            AnimatedVisibility(
                modifier = Modifier
                    .align( Alignment.BottomCenter),
                visible = invoiceViewModel.newDocumentState.value!=null,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(1000)),
//                modifier = Modifier.offset(100.dp, 0.dp)
            ) {
                Box(   modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(305.dp)
                    .fillMaxWidth()
                    .background(
                        Color.White,

                        shape = RoundedCornerShape(8.dp)
                    )
//        .clip(
//            shape = RoundedCornerShape(8.dp)
//        )
                    .padding(
                        10.dp
                    ) ,

                    ) {

                    Column( horizontalAlignment = Alignment.CenterHorizontally  ) {

                        NormalTextField(value =
                        invoiceViewModel.itemName.value,
                            onValueChange ={
                                invoiceViewModel.itemName.value =it
                               invoiceViewModel.valideteItemName()
                            } , placeholder ="Item name" ,

                           invoiceViewModel = invoiceViewModel
                        )
                        Spacer(modifier =

                        Modifier.height(
                            5.dp
                        )

                        )

                        NumberTextField(value =

                        invoiceViewModel.itemPrice.value.toString(),
                            onValueChange ={
                                invoiceViewModel.itemPrice.value =

                                    it.toDouble()
                                invoiceViewModel.validateItemPrice()
                            } ,
                            placeholder ="Item Price" ,

                            invoiceViewModel = invoiceViewModel
                        )
                        Spacer(modifier =

                        Modifier.height(
                            5.dp
                        )

                        )
                        NumberTextField(value =

                        invoiceViewModel.itemQuantity.value.toString(),
                            onValueChange ={
                                invoiceViewModel.itemQuantity.value =

                                    it.toInt()
                                invoiceViewModel.validateItemQuantity()
                            } ,
                            placeholder ="Quantity" ,

                            invoiceViewModel = invoiceViewModel
                        )
                        Spacer(modifier =

                        Modifier.height(
                            5.dp
                        )

                        )


                        MiniButton(text = "Add Item" ,

                            onClick = {


                                scope.launch {
//                                    invoiceViewModel.addInvoice().join()
//
//                                    if (invoiceViewModel.newDocumentState.value!=null){
//                                        enabled = true
//                                        var data =   invoiceViewModel.newDocumentState.value!!.snapshots().first()
//                                        titleState=  data.data!!["title"]!!.toString()
//
//
//
//                                    }
                                }


                            },
                            invoiceViewModel = invoiceViewModel
                            ,
                            isLoading = invoiceViewModel.newInvoiceState.value.isLoading ,
                            isEnabled = invoiceViewModel.isEnabledAddInvoceBtn.value
                        )

                    }





                }
            }

        }
    }


}
fun switch(enabled: Boolean) = if (enabled) 268.dp else -10.dp