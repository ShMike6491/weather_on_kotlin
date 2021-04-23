package com.e.weatherkotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            handleMessage(remoteMessage.data.toMap())
        }
    }

    private fun handleMessage(data: Map<String, String>) {
        val title = data[FIREBASE_MSG_TITLE]
        val msg = data[FIREBASE_MSG_MESSAGE]
        if(!title.isNullOrBlank() && !msg.isNullOrBlank()) {
            showNotification(title, msg)
        }
    }

    private fun showNotification(title: String, msg: String) {
        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, CHANNEL_ID).apply {
                setSmallIcon(R.drawable.ic_launcher_foreground)
                setContentTitle(title)
                setContentText(msg)
                priority = NotificationCompat.PRIORITY_DEFAULT
            }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(manager: NotificationManager) {
        val name = "MyChannel"
        val desc = "Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = desc
        }
        manager.createNotificationChannel(channel)
    }

    companion object {
        private const val FIREBASE_MSG_TITLE = "title"
        private const val FIREBASE_MSG_MESSAGE = "message"
        private const val CHANNEL_ID = "channel id"
        private const val NOTIFICATION_ID = 123
    }
}