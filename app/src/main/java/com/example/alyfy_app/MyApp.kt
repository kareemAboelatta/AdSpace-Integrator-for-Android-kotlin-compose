package com.example.alyfy_app

import android.app.Application
import android.util.Log
import com.example.alyfy_app.common.Constant
import com.rad.RXError
import com.rad.RXSDK
import com.rad.rcommonlib.utils.RXLogUtil


const val TAG = "RoulaxView"
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        val hasUserAuthorizedGDPR = true //

        RXSDK.setGDPRAuth(hasUserAuthorizedGDPR)

        // Initialize the SDK
        RXSDK.init(Constant.ROULAX_APP_ID, object : RXSDK.RXSDKInitListener {
            override fun onSDKInitFailure(error: RXError) {
                RXLogUtil.d("onSDKInitFailure ${error.msg}")
                Log.d(TAG,"onSDKInitFailure ${error.msg}")
            }

            override fun onSDKInitSuccess() {
                Log.d(TAG,"onSDKInitSuccessss")
            }


        })
    }
}
