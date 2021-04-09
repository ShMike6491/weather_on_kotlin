package com.e.weatherkotlin.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.e.weatherkotlin.R
import com.e.weatherkotlin.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private val MY_ACTION_BROADCAST = "com.e.weatherkotlin.BROADCAST_ACTION"

    private val receiver: MyBroadcastReceiver = MyBroadcastReceiver()
    private val testReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Custom Broadcast Receiver", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        registerReceiver(testReceiver, IntentFilter(MY_ACTION_BROADCAST))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_receiver -> {
                supportFragmentManager.apply {
                    sendBroadcast(Intent(MY_ACTION_BROADCAST))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        unregisterReceiver(testReceiver)
        super.onDestroy()
    }
}
