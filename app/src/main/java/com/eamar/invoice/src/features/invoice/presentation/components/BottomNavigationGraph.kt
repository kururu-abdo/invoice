package com.eamar.invoice.src.features.invoice.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


@Composable
fun BottomNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.home.path
    ) {

        addinvoicesScreen(navController, this)
        addFilesPage(navController, this)
        addProfileScreen(navController, this)
    }
}
private fun addinvoicesScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.home.path) {
        Invoices(
            navController = navController
//            navigateToHome = {
//                navController.navigate(NavRoute.Home.path)
//            }
        )
    }
}
private fun addProfileScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Profile.path) {
        ProfilePage(
            navController = navController
//            navigateToHome = {
//                navController.navigate(NavRoute.Home.path)
//            }
        )
    }
}
private fun addFilesPage(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.files.path) {
        FilesPage(
            navController = navController
//            navigateToHome = {
//                navController.navigate(NavRoute.Home.path)
//            }
        )
    }
}
sealed class NavRoute(val path: String) {

    object home: NavRoute("invoices")

    object files: NavRoute("Files")

    object Profile: NavRoute("profile") {
        val id = "id"
        val showDetails = "showDetails"
    }

//    object Search: NavRoute("search") {
//        val query = "query"
//    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}