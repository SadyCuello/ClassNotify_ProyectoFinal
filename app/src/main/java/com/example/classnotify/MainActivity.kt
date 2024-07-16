package com.example.classnotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.classnotify.navigation.NavManager
import com.example.classnotify.room.ClassDataBase
import com.example.classnotify.viewModels.MateriaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ){
                val database = Room.databaseBuilder(this, ClassDataBase::class.java, "db_classNotify").build()
                val dao = database.materiaDao()
                val viewModel = MateriaViewModel(dao = dao)
                NavManager(viewModel = viewModel)
            }
            }
        }
    }


