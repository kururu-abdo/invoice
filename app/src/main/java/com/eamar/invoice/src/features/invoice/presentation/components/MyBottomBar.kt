package com.eamar.invoice.src.features.invoice.presentation.components

import android.widget.Toast
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import javax.annotation.Untainted

@Composable
fun MyBottomBar(
//    onPageChange: (Int)->Unit ={}
    navController: NavController ,
) {

//    // items list
//    val bottomMenuItemsList = prepareBottomMenu()
//
//    val contextForToast = LocalContext.current.applicationContext
//
//    var selectedItem by remember {
//        mutableStateOf("Home")
//    }
//
//    BottomAppBar(
//        cutoutShape = CircleShape
//    ) {
//        bottomMenuItemsList.forEachIndexed { index, menuItem ->
//
//
//            BottomNavigationItem(
//                selected = (selectedItem == menuItem.label),
//                onClick = {
//                    selectedItem = menuItem.label
////                    Toast.makeText(
////                        contextForToast,
////                        menuItem.label, Toast.LENGTH_SHORT
////                    ).show()
//                    onPageChange (index)
//                },
//                icon = {
//                    Icon(
//                        imageVector = menuItem.icon,
//                        contentDescription = menuItem.label
//                    )
//                },
//                enabled = true
//            )
//        }
//    }
//
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

//    if (currentRoute == null ||
//        currentRoute == NavRoute.Login.path) {
//        return
//    }

    BottomNavigation {

        val homeSelected = currentRoute == NavRoute.home.path
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = NavRoute.home.path
                )
            },
            selected = homeSelected,
            onClick = {
//                if(!homeSelected) {
                    navController.navigate(NavRoute.home.path) {
                        popUpTo(NavRoute.home.path) { inclusive = true }
                    }
//                }
            },
            label = {Text(NavRoute.home.path)}
        )

        BottomNavigationItem(
            icon = {
                Icon(
                  painter = painterResource(id = com.eamar.invoice.R.drawable.ic_pdf),
                    contentDescription = NavRoute.files.path
                )
            },
            selected = homeSelected,
            onClick = {
//                if(!homeSelected) {
                    navController.navigate(NavRoute.files.path) {
                        popUpTo(NavRoute.files.path) { inclusive = true }
                    }
//                }
            },
            label = {Text(NavRoute.files.path)}
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = NavRoute.Profile.path
                )
            },
            selected = homeSelected,
            onClick = {
//                if(!homeSelected) {
                navController.navigate(NavRoute.Profile.path) {
                    popUpTo(NavRoute.Profile.path) { inclusive = true }
                }
//                }
            },
            label = {Text(NavRoute.Profile.path)}
        )
    }


}

private fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

    // add menu items
    bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home))
    bottomMenuItemsList.add(BottomMenuItem(label = "Files", icon = Icons.Filled.Home))


    bottomMenuItemsList.add(BottomMenuItem(label = "Profile", icon = Icons.Filled.Person))

    return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: ImageVector)