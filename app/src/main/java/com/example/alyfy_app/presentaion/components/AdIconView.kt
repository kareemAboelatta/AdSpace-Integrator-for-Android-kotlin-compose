package com.example.alyfy_app.presentaion.components

import android.util.Log
import android.widget.LinearLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.alyfy_app.common.Constant
import com.gamify.space.Gamify
import com.gamify.space.GamifyError


private const val TAG = "AdIconView"
@Composable
fun GamifyAdIcon() {
    val context = LocalContext.current
    AndroidView(
        factory = { ctx ->
            LinearLayout(ctx).apply {
                // This is where you add the Gamify icon view to the LinearLayout
                Gamify.setListener(object : Gamify.GamifyListener {
                    override fun onInitSuccess() {
                        Log.d(TAG, "SDK initialized successfully")
                        Gamify.loadIcon(Constant.PLACEMENT_ID)

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

                })
            }
        },
        update = { linearLayout ->
            // If you need to update the layout after receiving the ad
        }
    )
}