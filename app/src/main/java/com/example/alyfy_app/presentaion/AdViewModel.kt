package com.example.alyfy_app.presentaion

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.alyfy_app.common.Constant.APP_KEY
import com.example.alyfy_app.common.Constant.PLACEMENT_ID
import com.gamify.space.Gamify
import com.gamify.space.GamifyError

// AdViewModel.kt


private const val TAG = "AdViewModel"
class AdViewModel : ViewModel() {
    init {
        initializeGamifySDK()
    }

    private fun initializeGamifySDK() {
        // Initialize the Gamify SDK
        Gamify.initSDK(APP_KEY)
        Gamify.setListener(object : Gamify.GamifyListener {
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
    }

    companion object {
        private const val USER_ID = "Your Custom UserId" // Replace with the actual user ID
    }

}
