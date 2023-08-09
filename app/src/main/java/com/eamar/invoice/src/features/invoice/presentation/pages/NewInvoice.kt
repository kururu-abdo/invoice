package com.eamar.invoice.src.features.invoice.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel
import com.eamar.invoice.src.utils.Constants
import org.checkerframework.common.reflection.qual.NewInstance

@Composable
fun NewInvoice(navController: NavController = rememberNavController()
               ,
               authViewModel: AuthViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(


//            navigationIcon = {
//                IconButton(onClick = {
////                    scaffoldState.run {
////                        drawerState.open()
////
////                    }
//                    coroutineScope.launch {
//                        scaffoldState.drawerState.open()
//                    }
//
//
//                }) {
//                    Icon(Icons.Outlined.AccountBox ,
//
//                        contentDescription = ""
//                        )
//                }
//            },



            title = { Text("Invoice App") },


            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        Icons.Filled.ArrowBack ,
                        contentDescription = null

                    )
                }
            },
            backgroundColor = Constants.mainColor
        )  },


        ){contentPadding ->
        Box( modifier = Modifier.padding(
            bottom = contentPadding.calculateBottomPadding())
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

        }
    }
}