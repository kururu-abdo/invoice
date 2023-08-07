package com.eamar.invoice.src.features.auth.presentation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.eamar.invoice.src.features.auth.presentation.components.*
import com.eamar.invoice.src.utils.Constants.mainColor
import com.eamar.invoice.src.utils.Constants.thirdColor


@Composable
fun SignupPage (
    navController: NavController = rememberNavController()
    ,
    authViewModel: AuthViewModel = hiltViewModel()
){


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
                100.dp
            ))
            Column(
                modifier = Modifier
                    .height(
                        140.dp
                    ),

                Arrangement.SpaceBetween ,
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
                    .height(500.dp)
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


                    Text(text = "SignUp" ,
                        color = mainColor

                    )
                    Spacer(modifier =

                    Modifier.height(
                        20.dp
                    )

                    )

                    AuthTextField(value = authViewModel.userName.value,
                        onValueChange ={
                        authViewModel.userName.value=it
                        authViewModel.validateUserName()
                    } , placeholder ="User Name" ,

                        authViewModel = authViewModel ,
                        errMsg = authViewModel.userNameErrMsg.value
                    )
                    Spacer(modifier =

                    Modifier.height(
                        5.dp
                    )

                    )
                    EmailField(value = authViewModel.email.value, onValueChange ={
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

                    Spacer(modifier =

                    Modifier.height(
                        5.dp
                    )

                    )
                    ConfirmPasswordField(value = authViewModel.confirmPassword.value,
                        onValueChange ={
                            authViewModel.confirmPassword.value=it
authViewModel.validateConfirmPassword()

                    } , placeholder ="Confirm Password" ,
                        authViewModel = authViewModel
                    )

                    Spacer(modifier =

                    Modifier.height(
                        20.dp
                    )

                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text =  authViewModel.errMsg.value
                        ,
                        fontSize = 8.sp,
                        color = Color.Red
                    )
                    AuthButton(
                        text = "SignUp" ,
isEnabled = authViewModel.isEnabledRegisterButton.value,
                        onClick = {
//                            if (authViewModel.isEnabledRegisterButton.value){
                                authViewModel.signUser()
//                            }else {
//
//                            }
                        } ,


authViewModel = authViewModel ,
                        isLoading = authViewModel.authState.value.isLoading
                        )




                    Spacer(modifier =

                    Modifier.height(
                        15.dp
                    )

                    )
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "Already have account? ",color = Color(0xFFd5d5d5))
                        Text(text = "SignIn",

                            modifier=Modifier.clickable {
                                navController.navigate("login")
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