package com.example.image.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class AppNetwork(context: Context) : NetworkStatus {

    var context: Context? = context

    override fun isOnline(): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo = cm.activeNetworkInfo
        if (activeNetwork.isConnected) {
            return true
        }
        return false
    }

}