package com.eamar.invoice.src.features.invoice.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.utils.Constants.mainColor
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(navController: NavController ,

                authViewModel: AuthViewModel = hiltViewModel()
                ,
                mainNavController:NavController
                ){
    var scope = rememberCoroutineScope()

    authViewModel.  fetchProfile()
//    LaunchedEffect(Unit ){
//        authViewModel.  fetchProfile()
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                20.dp
            )

        ,
        Alignment.Center
    ) {
      if (authViewModel.authState.value.isLoading && authViewModel.isLoggedIn.value)
          CircularProgressIndicator()
       else if( !authViewModel.isLoggedIn.value)
           Text(text = "You are logged out")
        else
            Column(   modifier = Modifier.fillMaxSize(),
//Arrangement.SpaceEvenly

                horizontalAlignment=
Alignment.Start

                ) {

                Spacer(modifier = Modifier.height(50.dp))


              Box(

                  contentAlignment = Alignment.Center
              ) {


                  Row(modifier = Modifier

                      .height(110.dp)
                      ,
                   verticalAlignment=Alignment.CenterVertically
                      ,


                      horizontalArrangement=
                      Arrangement.Center ,

                  ) {

                      Image(painter = painterResource(id =
                      com.eamar.invoice.R.drawable.ic_user

                      )


                          ,
                          contentDescription =null ,

                          modifier = Modifier

                              .width(100.dp)
                              .height(100.dp)
                              .padding(4.dp, 4.dp)
                              .background(
                                  mainColor,
                                  CircleShape
                              )
                              .clip(CircleShape),
                          colorFilter =
                          ColorFilter.tint(Color.White),
                          contentScale = ContentScale.Crop,

                          )






                      Column(
                          horizontalAlignment = Alignment.Start
                      ) {
                          Text(


                              text = authViewModel.authState.value.user!!.userName.toString()
                              ,
                              style = TextStyle(
                                  fontSize = 20.sp  ,
                                  fontWeight = FontWeight.Bold
                              )

                          )

                          Text(


                              text = authViewModel.authState.value.user!!.email.toString()
                              ,
                              style = TextStyle(
                                  fontSize = 15.sp  ,

                                  )

                          )
                      }
                  }



              }
                Spacer(modifier = Modifier.height(20.dp))
Column(

    horizontalAlignment = Alignment.Start
) {
    Text(text = "Stats")
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth() ,

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Box( modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .background(
                color = mainColor,
                shape = RoundedCornerShape(10.dp)
            )

        ) {

        }



        Box( modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .background(
                color = mainColor,
                shape = RoundedCornerShape(10.dp)
            )

        ) {

        }
    }

}





                Spacer(modifier = Modifier.fillMaxSize(
                    fraction = .60f
                ))
                Column() {
                 Box (
                     modifier =
                     Modifier
                         .height(40.dp)
                         .width(100.dp)
                         .background(
                             color = Color(
                                 0xFFf5f5f7,


                                 ),
                             shape = RoundedCornerShape(8.dp),

                             )
                         .clickable {
                             scope.launch {
                                 authViewModel
                                     .logout(


                                     )
                                     .join()

                                 mainNavController.navigate("splash")

                             }


                         },

                     Alignment.Center
                         ){


             Row(


                 horizontalArrangement = Arrangement.Center ,


                 verticalAlignment = Alignment.CenterVertically
                 ) {

                 Icon(painter = painterResource(id =
                 com.eamar.invoice.R.drawable.ic_logout
                 ), contentDescription = "" ,

                     tint = Color.Red
                     )

                 Text(text = "Logout")
             }


                 }
                }

            }
    }
}