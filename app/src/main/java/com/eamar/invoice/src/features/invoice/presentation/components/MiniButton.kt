package com.eamar.invoice.src.features.invoice.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.features.invoice.presentation.InvoiceViewModel
import com.eamar.invoice.src.utils.Constants


@Composable
fun MiniButton(text: String, onClick: () -> Unit ,
               invoiceViewModel: InvoiceViewModel
               ,
               isLoading:Boolean= false ,
               isEnabled:Boolean = false
               ,


               ) {
//var isLoading by remember {
//    mutableStateOf(false)
//}

    Surface(elevation = 5.dp
        ,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Constants.thirdColor,
                            Constants.mainColor
                        )
                    ),

                    shape = RoundedCornerShape(8.dp),
                )
                .clickable {
                    if (isEnabled){
                        onClick()
                    }else {

                    }

                },

            contentAlignment = Alignment.Center
        ) {

            if (
                isLoading)

                CircularProgressIndicator(
                    strokeWidth = 2.dp ,
                    color= Color.White,
                    modifier = Modifier.height(
                        20.dp
                    )
                        .width(
                            20.dp
                        )
                )
            else
                Text(
                    text = text,

                    color = Color.White,
                )
        }
    }



//    Text(
//        text = text,
//        modifier = Modifier
//            .padding(12.dp)
//            .wrapContentSize()
//            .background(
//                Constants.mainColor
//            )
////            .background(ButtonDefaults.buttonColors().backgroundColor( ))
//            .padding(16.dp)
//            .clickable {
//                onClick()
//            },
//        color = Color.White,
////        onClick = onClick
//    )
}