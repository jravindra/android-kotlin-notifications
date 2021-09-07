package com.example.android.eggtimernotifications

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        Timber.d("Refreshed token: $token")
        sendRegistrationToServer(token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Timber.d("From: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Timber.d("Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let {
            Timber.d("Message Notification Body: ${it.body}")
            it.body?.let { it1 -> sendNotification(it1) }
        }
        super.onMessageReceived(remoteMessage)

    }

    private fun sendNotification(messageBody: String) {
        val notificationManager =
            ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager

        notificationManager.sendNotification(messageBody, applicationContext)

    }

    private fun sendRegistrationToServer(token: String?) {

    }
}