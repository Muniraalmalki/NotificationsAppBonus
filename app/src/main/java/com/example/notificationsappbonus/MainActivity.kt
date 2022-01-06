package com.example.notificationsappbonus

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.notificationsappbonus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var builder: Notification.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // When the Button is clicked, count down 5 seconds then show a notification indicating that the food is ready
        binding.btnStart.setOnClickListener {
            Handler().postDelayed({
                showNotification()
            }, 5000)
        }
    }

    private fun showNotification() {
        val channelId = "myapp.notifications"
        val description = "Notification App Example"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, NotificationActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setContentTitle("Egg Cooking Counter")
                .setContentText("Ready")
        } else {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setContentTitle("Egg Cooking Counter")
                .setContentText("Ready")
        }
        notificationManager.notify(1001, builder.build())
    }
}