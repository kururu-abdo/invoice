package com.eamar.invoice.src.features.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.eamar.invoice.src.utils.Constants
import com.eamar.invoice.ui.theme.InvoiceTheme


@Composable
fun AuthButton(text: String, onClick: () -> Unit) {


    Surface(elevation = 5.dp  ) {
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
                    onClick()
                },

            contentAlignment = Alignment.Center
        ) {


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