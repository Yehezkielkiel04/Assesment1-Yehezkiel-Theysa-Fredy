package com.yehezkiel0091.ceksuhu.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object About : Screen("aboutScreen")
}
