package com.example.classnotify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.classnotify.view.InicioView
import com.example.classnotify.viewModels.MateriaViewModel

@Composable

fun NavManager(
    viewModel: MateriaViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            InicioView(navController, viewModel)
        }
        composable("registrar") {
            AgregarMateriaView(navController, viewModel)
        }
        composable("editar/{idMateria}/{nombre}/{profesor}/{descripcion}/{horario}/{aula}/{adjunto}",
            arguments = listOf(
                navArgument("idMateria") { type = NavType.StringType },
                navArgument("nombre") { type = NavType.StringType },
                navArgument("profesor") { type = NavType.StringType },
                navArgument("descripcion") { type = NavType.StringType },
                navArgument("horario") { type = NavType.StringType },
                navArgument("aula") { type = NavType.StringType },
                navArgument("adjunto") { type = NavType.StringArrayType }
            )){ EditarMateriaView(
                navController,
                viewModel,
                it.arguments?.getString("idMateria")!!,
                it.arguments?.getString("nombre")!!,
                it.arguments?.getString("profesor")!!,
                it.arguments?.getString("descripcion")!!,
                it.arguments?.getString("horario")!!,
                it.arguments?.getString("aula")!!,
                it.arguments?.getByteArray("adjunto")!!,

            )

        }

    }
    }
