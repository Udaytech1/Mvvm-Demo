package com.task.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.task.R


class Commons {
    companion object {
        private var toast: Toast? = null
        /*Common function to show toast*/
        fun showToast(context: Context, msg: String) {
            toast?.cancel()
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
            toast?.show()
        }

        /* Common function to set image on imageview using url*/
        fun setImage(imageView: ImageView, url: String) {
            Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .error(R.drawable.ic_launcher_foreground).into(imageView)
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }
}