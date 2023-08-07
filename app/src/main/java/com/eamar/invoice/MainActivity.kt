package com.eamar.invoice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eamar.invoice.src.features.auth.presentation.pages.LoginPage
import com.eamar.invoice.src.features.auth.presentation.pages.SignupPage
import com.eamar.invoice.src.features.invoice.presentation.pages.Home
import com.eamar.invoice.ui.theme.InvoiceTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            InvoiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

//                    Greeting("Android")
                    NavHost(navController = navController, startDestination = "splash") {
                        composable("login") { LoginPage(
                            navController = navController
                        ) }
                        composable("signup") { SignupPage(  navController = navController) }
                        /*...*/

                        composable("splash") { WelcomePage(
                            navController = navController ,
                        ) }
                        composable("home") { Home(
                            navController = navController ,
                        ) }

                    }




                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InvoiceTheme {
        Greeting("Android")
    }
}