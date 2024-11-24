package com.example.classnotify.viewModels

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.classnotify.MainActivity
import com.example.classnotify.R
import com.example.classnotify.models.Anuncio
import com.example.classnotify.data.AnuncioDao
import com.example.classnotify.states.AnuncioStates
import kotlinx.coroutines.launch
import kotlin.random.Random

class AnuncioViewModel(application: Application, private val anuncioDao: AnuncioDao) : AndroidViewModel(application) {

    private val _anuncioStates = MutableLiveData(AnuncioStates())
    val anuncioStates: LiveData<AnuncioStates> get() = _anuncioStates

    init {
        loadAnuncios()
    }

    fun loadAnuncios() {
        viewModelScope.launch {
            _anuncioStates.value = AnuncioStates(isLoading = true)
            anuncioDao.getAllAnuncios().observeForever { anuncioList ->
                _anuncioStates.value = AnuncioStates(listaAnuncios = anuncioList)
            }
        }
    }

    fun agregarAnuncio(anuncio: Anuncio) {
        viewModelScope.launch {
            anuncioDao.insertAnuncio(anuncio)
            enviarNotificacion(anuncio)
            loadAnuncios()
        }
    }

    fun eliminarAnuncio(anuncio: Anuncio) {
        viewModelScope.launch {
            anuncioDao.deleteAnuncio(anuncio)
            loadAnuncios()
        }
    }

    private fun enviarNotificacion(anuncio: Anuncio) {
        val context = getApplication<Application>().applicationContext
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = context.getString(R.string.default_notificacion_channel_id)
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Nuevo Anuncio")
            .setContentText("${anuncio.titulo}: ${anuncio.descripcion}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.vector)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Notificaciones de Anuncios"
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(Random.nextInt(), notificationBuilder.build())
    }
}
