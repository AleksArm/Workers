package com.highestaim.workers.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.highestaim.workers.enums.InternetEnum
import com.highestaim.workers.enums.InternetEnum.*

object Utils {

    fun isOnline(context: Context?): Pair<Boolean, InternetEnum> {
        val result: Pair<Boolean, InternetEnum>

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return Pair(false, NONE)
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)
                ?: return Pair(false, NONE)
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Pair(true, WIFI)
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Pair(true, MOBILE)
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Pair(true, ETHERNET)
                }
                else -> {
                    Pair(false, NONE)
                }
            }

        return result
    }
}