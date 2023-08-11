package com.eamar.invoice.src.features.invoice.presentation.pages

import android.widget.ImageButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eamar.invoice.src.features.invoice.presentation.components.BottomNavigationGraph
import com.eamar.invoice.src.features.invoice.presentation.components.MyBottomBar
import com.eamar.invoice.src.utils.Constants.mainColor
import kotlinx.coroutines.launch


@Composable
fun Home(
    navController: NavController
) {
   var currentPage by remember {
       mutableStateOf(0)
   }
    val bottomNavController = rememberNavController()
    var pages = listOf(
        Box() {
            Text(text = "Home")
        },
        Box() {
            Text(text = "Files")
        },
        Box() {
            Text(text = "Profile")
        }

    )
    val coroutineScope = rememberCoroutineScope()

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



            title = {Text("Invoice App")},


            actions = {
                IconButton(onClick = {

                    navController.navigate("add-invoice")
                }) {
                    Icon(Icons.Filled.Add ,
                    contentDescription = null

                        )
                }
            },
                backgroundColor = mainColor)  },

//        drawerContent = { Text(text = "drawerContent") },
//        content = {
////                  it.
//            Text(text="BodyContent")
//        } ,
        bottomBar = { MyBottomBar(
//            onPageChange = {
//                it ->
//                currentPage=it
//            }
        navController = bottomNavController
        )
                    },

    ){contentPadding ->
//        contentPadding.calculateBottomPadding()
//      pages.get(currentPage)
//        [currentPage]
        BottomNavigationGraph(
            modifier = Modifier.padding(
                bottom = contentPadding.calculateBottomPadding()),

            navController = bottomNavController,
            mainNavController = navController
        )
    }
}