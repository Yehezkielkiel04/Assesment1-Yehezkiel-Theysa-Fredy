package com.yehezkiel0091.ceksuhu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yehezkiel0091.ceksuhu.navigation.Screen
import com.yehezkiel0091.ceksuhu.ui.screen.AboutScreen
import com.yehezkiel0091.ceksuhu.ui.screen.MainScreen
import com.yehezkiel0091.ceksuhu.ui.theme.CekSuhuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CekSuhuTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) { MainScreen(navController) }
                    composable(Screen.About.route) { AboutScreen(navController) }
                }
            }
        }
    }
}
