package com.eamar.invoice.src.features.invoice.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eamar.invoice.R
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.features.invoice.data.model.InvoiceItem
import com.eamar.invoice.src.utils.Constants


@Composable
fun Invoices(navController: NavController
,
             mainNavController:NavController ,
             authViewModel: AuthViewModel=  hiltViewModel()
) {

    var invoices = listOf<IncoiveItem2>(
        IncoiveItem2(
            title = "Invoice 34"
        ),
        IncoiveItem2(
            title = "Invoice 34"
        )
    ,
        IncoiveItem2(
            title = "Invoice 34"
        )
    )
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(content ={
            items(invoices.size)
            {
                InvoiceItemUI(invoiceItem = invoices[it])
            }

        } )
    }
}
@Composable
fun InvoiceItemUI(invoiceItem: IncoiveItem2){
    Surface( elevation = 6.dp) {
        Box(
            Modifier
                .padding(
                    bottom =                 10.dp

        )
                .fillMaxWidth()
                .fillMaxHeight(fraction = .2f)
                .background(
                    color = Color(
                        0xFFf5f5f7,


                        )
                )
                .padding(15.dp)

        ) {

            Column (horizontalAlignment = Alignment.Start) {

               Row(verticalAlignment = Alignment.CenterVertically  ) {
                   Image(painter = painterResource(id =
                   R.drawable.ic_user

                   )


                       ,
                       contentDescription =null ,

                       modifier = Modifier

                           .width(80.dp)
                           .height(80.dp)
                           .padding(4.dp, 4.dp)

                           .clip(CircleShape),
//                       colorFilter =
//                       ColorFilter.tint(Color.White),
                       contentScale = ContentScale.Crop,

                       )
                   Column(  horizontalAlignment = Alignment.Start) {
                       Text(text = invoiceItem.title ,
                       style = TextStyle(
                           fontSize = 18.sp ,
                           fontWeight = FontWeight.Bold
                       )

                           )
                       Text(text = invoiceItem.date ,
                           style = TextStyle(
                               fontSize = 18.sp ,
                               fontWeight = FontWeight.Bold
                           )

                       )
                   }
               }


                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween  ) {

                    Box() {

                        Column(horizontalAlignment = Alignment.CenterHorizontally  ) {
                            Text(text = "Total Items"

                                 ,   style = TextStyle(
                                    fontSize = 18.sp ,
                                fontWeight = FontWeight.Bold
                            )

                            )
                            Text(text = invoiceItem.totalItems.toString()

                                ,   style = TextStyle(
                                    fontSize = 15.sp ,

                                )

                            )

                        }


                    }
                    Box() {

                        Column(horizontalAlignment = Alignment.CenterHorizontally  ) {
                            Text(text = "Total Price"

                                ,   style = TextStyle(
                                    fontSize = 18.sp ,
                                    fontWeight = FontWeight.Bold
                                )

                            )
                            Text(text = invoiceItem.totalPrice.toString()

                                ,   style = TextStyle(
                                    fontSize = 15.sp ,

                                    )

                            )

                        }

                    }
                }
            }


        }
    }
}
data class  IncoiveItem2(
    var title :String ,
    var date:String = "1/1/2023" ,
    var totalPrice:Double = 455.6 ,
    var totalItems:Int = 4
)