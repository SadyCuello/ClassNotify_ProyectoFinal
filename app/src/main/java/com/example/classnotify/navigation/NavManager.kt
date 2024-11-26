package com.example.classnotify.navigation

import AgregarMateriaView
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.classnotify.domain.models.Materia
import com.example.classnotify.ui_presentation.ui.view.EditarMateriaView
import com.example.classnotify.ui_presentation.ui.view.InicioView
import com.example.classnotify.ui_presentation.ui.view.PublicarAnuncioView
import com.example.classnotify.domain.viewModels.AnuncioViewModel
import com.example.classnotify.domain.viewModels.MateriaViewModel
import com.example.classnotify.ui_presentation.ui.view.VerAnunciosView

@Composable
fun NavManager(
    navController: NavHostController,
    materiaViewModel: MateriaViewModel,
    anuncioViewModel: AnuncioViewModel
) {
    var materia by remember { mutableStateOf<Materia?>(null) }
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            InicioView(navController, materiaViewModel, anuncioViewModel)
        }
        composable("agregarMateria") {
            AgregarMateriaView(navController, materiaViewModel)
        }
        composable("editarMateria/{idMateria}") { backStackEntry ->
            val idMateria = backStackEntry.arguments?.getString("idMateria")?.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid ID")
            EditarMateriaView(
                navController = navController,
                viewModel = materiaViewModel,
                idMateria = idMateria
            )
        }
        composable("publicarAnuncio") {
            PublicarAnuncioView(navController, anuncioViewModel)
        }
        composable("verAnuncios") { VerAnunciosView(navController, anuncioViewModel) }
    }
}
