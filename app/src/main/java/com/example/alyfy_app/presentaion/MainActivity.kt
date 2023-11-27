package com.example.alyfy_app.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.alyfy_app.common.ui.theme.Alyfy_AppTheme
import androidx.activity.viewModels

import com.example.alyfy_app.presentaion.screens.MainScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Alyfy_AppTheme {
                val adViewModel: AdViewModel by viewModels()
                MainScreen(adViewModel)
            }
        }
    }


}


