package com.example.classnotify.view

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.classnotify.viewModels.MateriaViewModel

@Composable
fun InicioView(navController: NavController, viewModel: ViewModel){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(text = "ClassNotify")
                })
        }
    )
}