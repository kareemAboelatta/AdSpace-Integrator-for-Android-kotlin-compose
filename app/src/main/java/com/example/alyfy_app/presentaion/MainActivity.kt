package com.example.alyfy_app.presentaion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.alyfy_app.presentaion.theme.Alyfy_AppTheme
import android.widget.LinearLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.alyfy_app.Constants.APP_KEY
import com.example.alyfy_app.Constants.PLACEMENT_ID
import com.gamify.space.Gamify
import com.gamify.space.GamifyError
import com.gamify.space.Gamify.GamifyListener


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Gamify SDK
        Gamify.initSDK(APP_KEY)
        Gamify.setListener(object : GamifyListener {
            override fun onInitSuccess() {
                Log.d(TAG, "SDK initialized successfully")
                Gamify.loadIcon(PLACEMENT_ID)
            }

            override fun onInitFailed(error: GamifyError) {
                Log.d(TAG, "SDK initialization failed: ${error.msg}")
            }

            override fun onIconReady(placement: String) {
                Log.d(TAG, "Icon ready for placement: $placement")

            }

            override fun onIconLoadFailed(placement: String, error: GamifyError) {
                Log.d(TAG, "Icon load failed for placement: $placement with error: ${error.msg}")
            }

            override fun onIconShowFailed(placementId: String, error: GamifyError) {
                Log.d(
                    TAG,
                    "Icon show failed for placementId: $placementId with error: ${error.msg}"
                )
            }

            override fun onIconClick(placement: String) {
                Log.d(TAG, "Icon clicked for placement: $placement")
            }

            override fun onInteractiveOpen(placement: String) {
                Log.d(TAG, "Interactive ad opened for placement: $placement")
            }

            override fun onInteractiveOpenFailed(placementId: String, error: GamifyError) {
                Log.d(
                    TAG,
                    "Interactive ad open failed for placementId: $placementId with error: ${error.msg}"
                )
            }

            override fun onInteractiveClose(placement: String) {
                Log.d(TAG, "Interactive ad closed for placement: $placement")
            }

            override fun onOfferWallOpen(placementId: String) {
                Log.d(TAG, "Offer Wall opened for placementId: $placementId")
            }

            override fun onOfferWallOpenFailed(placementId: String, error: GamifyError) {
                Log.d(
                    TAG,
                    "Offer Wall open failed for placementId: $placementId with error: ${error.msg}"
                )
            }

            override fun onOfferWallClose(placementId: String) {
                Log.d(TAG, "Offer Wall closed for placementId: $placementId")
            }

            override fun onGSpaceOpen(placementId: String) {
                Log.d(TAG, "GSpace opened for placementId: $placementId")
            }

            override fun onGSpaceOpenFailed(placementId: String, error: GamifyError) {
                Log.d(
                    TAG,
                    "GSpace open failed for placementId: $placementId with error: ${error.msg}"
                )
            }

            override fun onGSpaceClose(placementId: String) {
                Log.d(TAG, "GSpace closed for placementId: $placementId")
            }

            override fun onUserInteraction(placementId: String, interaction: String) {
                Log.d(
                    TAG,
                    "User interaction for placementId: $placementId with interaction: $interaction"
                )
            }
        })

        Gamify.setUserId(USER_ID)



        setContent {
            Alyfy_AppTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }

    companion object {


        private const val USER_ID = "Your Custom UserId" // Replace with the actual user ID
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    val companies = listOf("OKSpin", "Roulax") // List of company names

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            pageCount = companies.size,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            when (page) {
                0 -> ShowAdIcon() // OKSpin
                1 -> RoulaxPlaceholder() // Roulax
            }
        }

        HorizontalPager(
            state = pagerState,
            pageCount = companies.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) { i ->
            Text(
                text = companies[pagerState.currentPage],
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }


    }
}

@Composable
fun RoulaxPlaceholder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text("Roulax", style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun ShowAdIcon() {
    val context = LocalContext.current
    AndroidView(
        factory = { ctx ->
            LinearLayout(ctx).apply {
                // This is where you add the Gamify icon view to the LinearLayout
                Gamify.setListener(object : GamifyListener {
                    override fun onInitSuccess() {
                        Log.d(TAG, "SDK initialized successfully")
                        Gamify.loadIcon(PLACEMENT_ID)

                    }

                    override fun onIconReady(placement: String) {
                        val iconView = Gamify.showIcon(placement)
                        iconView?.let { view ->
                            addView(view.apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    400,
                                    400
                                )
                            })
                        }
                    }

                    override fun onInitFailed(error: GamifyError) {
                        Log.d(TAG, "SDK initialization failed: ${error.msg}")
                    }


                    override fun onIconLoadFailed(placement: String, error: GamifyError) {
                        Log.d(
                            TAG,
                            "Icon load failed for placement: $placement with error: ${error.msg}"
                        )
                    }

                    override fun onIconShowFailed(placementId: String, error: GamifyError) {
                        Log.d(
                            TAG,
                            "Icon show failed for placementId: $placementId with error: ${error.msg}"
                        )
                    }

                    override fun onIconClick(placement: String) {
                        Log.d(TAG, "Icon clicked for placement: $placement")
                    }

                    override fun onInteractiveOpen(placement: String) {
                        Log.d(TAG, "Interactive ad opened for placement: $placement")
                    }

                    override fun onInteractiveOpenFailed(placementId: String, error: GamifyError) {
                        Log.d(
                            TAG,
                            "Interactive ad open failed for placementId: $placementId with error: ${error.msg}"
                        )
                    }

                    override fun onInteractiveClose(placement: String) {
                        Log.d(TAG, "Interactive ad closed for placement: $placement")
                    }

                    override fun onOfferWallOpen(placementId: String) {
                        Log.d(TAG, "Offer Wall opened for placementId: $placementId")
                    }

                    override fun onOfferWallOpenFailed(placementId: String, error: GamifyError) {
                        Log.d(
                            TAG,
                            "Offer Wall open failed for placementId: $placementId with error: ${error.msg}"
                        )
                    }

                    override fun onOfferWallClose(placementId: String) {
                        Log.d(TAG, "Offer Wall closed for placementId: $placementId")
                    }

                    override fun onGSpaceOpen(placementId: String) {
                        Log.d(TAG, "GSpace opened for placementId: $placementId")
                    }

                    override fun onGSpaceOpenFailed(placementId: String, error: GamifyError) {
                        Log.d(
                            TAG,
                            "GSpace open failed for placementId: $placementId with error: ${error.msg}"
                        )
                    }

                    override fun onGSpaceClose(placementId: String) {
                        Log.d(TAG, "GSpace closed for placementId: $placementId")
                    }

                    override fun onUserInteraction(placementId: String, interaction: String) {
                        Log.d(
                            TAG,
                            "User interaction for placementId: $placementId with interaction: $interaction"
                        )
                    }

                    // ... Implement other GamifyListener methods
                })
            }
        },
        update = { linearLayout ->
            // If you need to update the layout after receiving the ad
        }
    )
}