package com.navi.musicplayerapp.data.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.data.broadcast.BroadcastMusicReceiver
import com.navi.musicplayerapp.data.utils.Constants.ACTION_NEXT
import com.navi.musicplayerapp.data.utils.Constants.ACTION_PAUSE
import com.navi.musicplayerapp.data.utils.Constants.ACTION_PLAY
import com.navi.musicplayerapp.data.utils.Constants.ACTION_PREVIOUS
import com.navi.musicplayerapp.data.utils.Constants.CHANNEL_ID
import com.navi.musicplayerapp.data.utils.Constants.MUSIC_NOTIFICATION_CHANNEL_NAME
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.ui.uidefault.MainActivity

fun notificationMusic(
    context: Context,
    track: TrackEntity,
    application: Application,
    isPlaying: Boolean
): Notification {

    createNotificationChannel(context)
    val remoteViews = RemoteViews(context.packageName, R.layout.notification_view)
    remoteViews.setTextViewText(R.id.title, track.title)
    remoteViews.setTextViewText(R.id.artist, track.artistName)
    if (isPlaying) {
        remoteViews.setViewVisibility(R.id.play, View.GONE)
        remoteViews.setViewVisibility(R.id.pause, View.VISIBLE)
    } else {
        remoteViews.setViewVisibility(R.id.play, View.VISIBLE)
        remoteViews.setViewVisibility(R.id.pause, View.GONE)
    }

    val notificationIntent = Intent(context, MainActivity::class.java)
    notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

    val pendingIntent = PendingIntent.getActivity(
        context,
        0, notificationIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    remoteViews.setOnClickPendingIntent(R.id.play, broadcastPendingIntent(application, ACTION_PLAY))
    remoteViews.setOnClickPendingIntent(R.id.pause, broadcastPendingIntent(application, ACTION_PAUSE))
    remoteViews.setOnClickPendingIntent(R.id.next, broadcastPendingIntent(application, ACTION_NEXT))
    remoteViews.setOnClickPendingIntent(R.id.previous, broadcastPendingIntent(application, ACTION_PREVIOUS))

    return NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setStyle(NotificationCompat.DecoratedCustomViewStyle())
        .setContentIntent(pendingIntent)
        .setCustomContentView(remoteViews)
        .setDefaults(0)
        .build()
}


private fun createNotificationChannel(context: Context) {
    val name = MUSIC_NOTIFICATION_CHANNEL_NAME
    val importance = NotificationManager.IMPORTANCE_NONE

    val channel = NotificationChannel(CHANNEL_ID, name, importance)

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

    notificationManager?.createNotificationChannel(channel)
}

private fun broadcastPendingIntent(application: Application, action: String): PendingIntent =
    PendingIntent.getBroadcast(application, 0, Intent(
        application, BroadcastMusicReceiver::class.java
    ).apply { this.action = action }, PendingIntent.FLAG_IMMUTABLE)

