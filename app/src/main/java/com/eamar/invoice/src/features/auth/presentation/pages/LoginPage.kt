package com.eamar.invoice.src.features.auth.presentation.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.features.auth.presentation.components.AuthButton
import com.eamar.invoice.src.features.auth.presentation.components.AuthTextField
import com.eamar.invoice.src.features.auth.presentation.components.EmailField
import com.eamar.invoice.src.features.auth.presentation.components.PasswordField
import com.eamar.invoice.src.utils.Constants.mainColor
import com.eamar.invoice.src.utils.Constants.thirdColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    navController: NavController = rememberNavController()
    ,
    authViewModel: AuthViewModel = hiltViewModel()
){
    val context = LocalContext.current


    var scope = rememberCoroutineScope()
    LaunchedEffect(authViewModel.authState){
        if (authViewModel.errMsg.value.isNotEmpty()){
            Toast.makeText(
                context ,
                authViewModel.errMsg.value ,
                Toast.LENGTH_SHORT
            ).show()
        }
        if (authViewModel.authState.value.user!= null){
            navController.navigate("home")
        }
//    isLoading=authViewModel.authState.value.isLoading
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    thirdColor,
                    mainColor
                )
            )
        )
        .padding(
            20.dp
        )

,
 contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
             ,
            Arrangement.SpaceBetween ,
            Alignment.CenterHorizontally
        ) {
            Spacer(modifier =Modifier.height(
                120.dp
            ))
            Column(
                modifier = Modifier
                    .height(
                        135.dp
                    ),

                        Arrangement.SpaceBetween,
                Alignment.CenterHorizontally
            ){
                Image(painter =
                painterResource(id = com.eamar.invoice.R.drawable.bill)
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

            Spacer(modifier =Modifier.height(
                15.dp
            ))

Box(

    modifier = Modifier
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



    Column(


        modifier = Modifier.fillMaxSize() ,

       horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Login" ,
        color = mainColor

            )
        Spacer(modifier =

        Modifier.height(
            20.dp
        )

        )

        EmailField(value = authViewModel.email.value,
            onValueChange ={
            authViewModel.email.value =it
            authViewModel.validateEmail()
        } , placeholder ="Email" ,

            authViewModel = authViewModel
        )
        Spacer(modifier =

        Modifier.height(
            5.dp
        )

        )
        PasswordField(value = authViewModel.password.value,
            onValueChange ={
                authViewModel.password.value =it
                authViewModel.validatePassword()
            } , placeholder ="Password" , authViewModel = authViewModel
        )
        Row(modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier

                .padding(5.dp),
                text = "Forget Password?" ,

                style = TextStyle(
                    fontSize= 8.sp ,
                    color = thirdColor
                )
                )
        }


        Spacer(modifier =

        Modifier.height(
            10.dp
        )

        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text =  authViewModel.errMsg.value
            ,
            fontSize = 8.sp,
            color = Color.Red
        )
        AuthButton(text = "Login" ,

            onClick = {
                scope.launch {
                    authViewModel.loginUser().join()


                    if (authViewModel.authState.value.user!= null){
                        navController.navigate("home")
                    }
                }




            },
            authViewModel = authViewModel
,
            isLoading = authViewModel.authState.value.isLoading ,
            isEnabled = authViewModel.isEnabledLoginButton.value
        )
        Spacer(modifier =

        Modifier.height(
            15.dp
        )

        )
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "New to Invoices? ",color = Color(0xFFd5d5d5))
            Text(text = "SignUp",

                modifier=Modifier.clickable {
                  navController.navigate("signup")
                },

                color = thirdColor)
        }
//        Text(
//            buildAnnotatedString {
//
//                withStyle(style = SpanStyle(color = Color(0xFFd5d5d5))) {
//                    append("New to Invoices?")
//                }
//                withStyle(
//
//                    style = SpanStyle(color = thirdColor)
//
//                 ,
//
//
//                ) {
//                    append("SignUp")
//                }
//            }
//                )
    }


}






        }

    }
}