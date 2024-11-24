package com.example.classnotify

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.classnotify.navigation.NavManager
import com.example.classnotify.room.AnuncioDataBase
import com.example.classnotify.room.ClassDataBase
import com.example.classnotify.viewModels.AnuncioViewModel
import com.example.classnotify.viewModels.MateriaViewModel
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private companion object {
        const val REQUEST_CODE = 100
    }

    private lateinit var anuncioViewModel: AnuncioViewModel
    private lateinit var materiaViewModel: MateriaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestStoragePermissions()

        val classDataBase = Room.databaseBuilder(
            applicationContext,
            ClassDataBase::class.java,
            "class_database"
        ).build()

        val materiaDao = classDataBase.materiaDao()

        val anuncioDataBase = Room.databaseBuilder(
            applicationContext,
            AnuncioDataBase::class.java,
            "anuncio_database"
        ).build()

        val anuncioDao = anuncioDataBase.anuncioDao()

        // Instanciar ViewModels
        anuncioViewModel = AnuncioViewModel(application, anuncioDao)
        materiaViewModel = MateriaViewModel(materiaDao)

        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                NavManager(navController, materiaViewModel, anuncioViewModel)
            }
        }
    }

    private fun requestStoragePermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de almacenamiento concedido", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    this,
                    "Permiso de almacenamiento denegado. La aplicación puede no funcionar correctamente.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
