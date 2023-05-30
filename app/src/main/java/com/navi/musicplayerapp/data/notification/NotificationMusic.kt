package com.navi.musicplayerapp.data.notification

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.navi.musicplayerapp.R
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
        0, notificationIntent, PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    remoteViews.setOnClickPendingIntent(R.id.play, broadcastPendingIntent(application, ACTION_PLAY.hashCode()))
    remoteViews.setOnClickPendingIntent(R.id.pause, broadcastPendingIntent(application, ACTION_PAUSE.hashCode()))
    remoteViews.setOnClickPendingIntent(R.id.next, broadcastPendingIntent(application, ACTION_NEXT.hashCode()))
    remoteViews.setOnClickPendingIntent(R.id.previous, broadcastPendingIntent(application, ACTION_PREVIOUS.hashCode()))

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
    val importance = NotificationManager.IMPORTANCE_HIGH

    val channel = NotificationChannel(CHANNEL_ID, name, importance)

    // add channel
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

    notificationManager?.createNotificationChannel(channel)
}

private fun broadcastPendingIntent(application: Application, actionCode: Int): PendingIntent =
    PendingIntent.getBroadcast(application, actionCode, Intent(
        // TODO() Broadcast and putExtra action
    ), PendingIntent.FLAG_IMMUTABLE)

