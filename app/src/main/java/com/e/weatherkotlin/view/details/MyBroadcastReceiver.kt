package com.e.weatherkotlin.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val noConnectivity = intent!!.getBooleanExtra(
            ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
        )
        var msg = if(noConnectivity) "Disconnected" else "Connected"
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}