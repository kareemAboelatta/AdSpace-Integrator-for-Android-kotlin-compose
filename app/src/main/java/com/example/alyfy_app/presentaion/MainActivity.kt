package com.example.alyfy_app.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.alyfy_app.common.ui.theme.Alyfy_AppTheme
import androidx.activity.viewModels
import com.example.alyfy_app.common.Constant

import com.example.alyfy_app.presentaion.screens.MainScreen
import com.rad.RXError
import com.rad.RXSDK
import com.rad.playercommon.ui.RoulaxVideoView
import com.rad.rcommonlib.utils.RXLogUtil


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Alyfy_AppTheme {
                val adViewModel: AdViewModel by viewModels()
                MainScreen(adViewModel = adViewModel,context =this)
            }
        }
    }


}


