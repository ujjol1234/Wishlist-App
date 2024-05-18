package com.example.wishlistapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wishlistapp.AddEditScreen
import com.example.wishlistapp.Homeview
import com.example.wishlistapp.WishViewModel

@Composable
fun Navigation(navcontroller: NavHostController = rememberNavController(),viewModel: WishViewModel){


    NavHost(navController = navcontroller, startDestination = "Homeview"){
        composable("Homeview"){
            Homeview(navController = navcontroller, viewModel = WishViewModel())
        }
        composable("Edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })){
            backStackEntry ->
            val ID = backStackEntry.arguments?.getLong("id") ?: 0L
            AddEditScreen(id=ID, navcontroller = navcontroller, viewmodel = viewModel)
        }

    }

}