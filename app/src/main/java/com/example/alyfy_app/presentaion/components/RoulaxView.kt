package com.example.alyfy_app.presentaion.components

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.alyfy_app.TAG
import com.example.alyfy_app.common.Constant
import com.google.android.gms.common.ConnectionResult.TIMEOUT
import com.rad.RXError
import com.rad.RXSDK
import com.rad.out.RXAdInfo
import com.rad.out.RXSdkAd
import com.rad.out.banner.RXBannerAd
import com.rad.out.banner.RXBannerEventListener
import com.rad.out.interstitial.RXInterstitialAd
import com.rad.out.nativeicon.RXNativeIconAd
import com.rad.out.reward.RXRewardVideoAd
import com.rad.out.reward.RXRewardVideoEventListener
import com.rad.out.splash.RXSplashAd
import com.rad.out.splash.RXSplashEventListener
import com.rad.rcommonlib.utils.RXLogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun RoulaxSplashAd(context: Context, unitId: String) {
    var adView by remember { mutableStateOf<View?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // Ensure SDK is initialized before this Composable is called
    LaunchedEffect(Unit) {

        coroutineScope.launch {
            // Wait asynchronously for SDK initialization
            RXSDK.awaitInitialization() // This should be a suspend function you create
            Log.d(TAG, "RXSDK.isInitialized()")

            // Now load the splash ad
            RXSDK.createRXSdkAd().loadSplash(
                unitId,
                6,
                object : RXSdkAd.RXSplashAdListener {
                    override fun success(adInfo: RXAdInfo, splashAd: RXSplashAd) {
                        // If splash ad is ready, get the view and show it
                        if (splashAd.isReady()) {
                            adView = splashAd.getSplashView(context as Activity)
                        }

                        Log.d(TAG, "object : RXSdkAd.RXSplashAdListener:success 11 ")

                    }

                    override fun failure(adInfo: RXAdInfo, error: RXError) {
                        Log.e(TAG, "Failed to load splash ad:  1 ${error.msg}")
                    }

                    override fun timeout(adInfo: RXAdInfo) {
                        Log.e(TAG, "Splash ad load timeout1 ")
                    }
                }
            )
        }

    }

    // Display the ad view if it's ready
    adView?.let { view ->
        AndroidView(
            factory = { view },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        )
    } ?: Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Loading Roulax ...",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Not working yet and It  won't work because there's no Matching between Unit id and Unit" +
                " type and when i match type with id like icon native type with 490 it match" +
                "but i got failure 'No Bid'. i asked the HR and he said OK. ")
    }
}





// Extension function on RXSDK to suspend until initialized
suspend fun RXSDK.awaitInitialization() {
    while (!isInitialized()) {
        delay(100) // Suspend for a short time before checking again
    }
}



