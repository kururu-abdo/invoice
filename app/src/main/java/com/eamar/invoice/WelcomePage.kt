package com.eamar.invoice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.utils.Constants
import kotlinx.coroutines.delay


@Composable
fun WelcomePage (
    navController: NavController = rememberNavController( ) ,
    authViewModel: AuthViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = Unit){
        delay(3000)
        if (authViewModel.isLoggedIn.value){
            navController.navigate("home")
        }else {
            navController.navigate("login")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Constants.thirdColor,
                        Constants.mainColor
                    )
                )
            ),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier

                    .height(
                    140.dp
                    ),

            Arrangement.SpaceBetween ,
            Alignment.CenterHorizontally
        ){

            Image(painter =

            painterResource(id = R.drawable.bill)
                , contentDescription ="" ,

                modifier = Modifier.height(
                    115.dp
                )
            )


            Text(text = "Invoice App " ,

                style = TextStyle(
                    fontSize = 18.sp ,
                    color = Color.Black
                )
            )

        }
    }
}