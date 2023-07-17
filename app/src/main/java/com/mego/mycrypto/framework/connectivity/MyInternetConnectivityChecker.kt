package com.mego.mycrypto.framework.connectivity

import android.app.Activity
import android.app.Application
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.DefaultLifecycleObserver
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class MyInternetConnectivityChecker (private val application: Application)
    : Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    var isConnected = false
    private set

    private var mNetworkCallbackRegistered = false

    private val networkRequest : NetworkRequest
    private val mNetworkCallback : NetworkCallback

    init {
        application.registerActivityLifecycleCallbacks(this)

        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
            .build()

        mNetworkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                if (isInternetAvailable()) {
                    isConnected = true
                    Toast.makeText(application, "Internet is connected.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isConnected = false
                Toast.makeText(application, "Internet is disconnected.", Toast.LENGTH_LONG).show()
            }

        }
        registerForNetworkChange()
    }

    /*
    private fun isDeviceOnline(): Boolean {
        val connManager = application.getSystemService(ConnectivityManager::class.java)
        var isOnline = false

        val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
        if ( networkCapabilities == null ) {
            Toast.makeText(application, "Internet is disconnected.", Toast.LENGTH_LONG).show()
            return false
        } else {
            isOnline = //networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    //networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                    //networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED) &&
                    isInternetAvailable()
        }
        Toast.makeText(application, "Internet is connected. -> $isOnline", Toast.LENGTH_LONG).show()
        return isOnline
    }
    */

    private fun isInternetAvailable(): Boolean {
        val urlConnection =
            URL("https://clients3.google.com/generate_204").openConnection() as HttpsURLConnection
        try {
            urlConnection.setRequestProperty("User-Agent", "Android")
            urlConnection.setRequestProperty("Connection", "close")
            urlConnection.connectTimeout = 1000
            urlConnection.connect()
            return urlConnection.responseCode == 204
        } catch (e: Exception) {
            return false
        }
    }

    private fun registerForNetworkChange() {
        if (!mNetworkCallbackRegistered) {
            val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
            mNetworkCallbackRegistered = true
            connectivityManager.registerNetworkCallback(networkRequest, mNetworkCallback)
        }
    }

    private fun unregisterForNetworkChange() {
        if (mNetworkCallbackRegistered) {
            val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
            mNetworkCallbackRegistered = false
            connectivityManager.unregisterNetworkCallback(mNetworkCallback)
        }
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityResumed(p0: Activity) {
        registerForNetworkChange()
    }

    override fun onActivityPaused(p0: Activity) {
        unregisterForNetworkChange()
    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {

    }

}