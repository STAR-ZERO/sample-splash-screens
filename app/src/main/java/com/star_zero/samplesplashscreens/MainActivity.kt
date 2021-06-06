package com.star_zero.samplesplashscreens

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Check Version

        // Wait
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.isReady) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )

        // Exit Animation
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val animatorSet = AnimatorSet()
            animatorSet.play(
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.SCALE_X,
                    1f,
                    20f,
                )
            ).with(
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.SCALE_Y,
                    1f,
                    20f,
                )
            )
            animatorSet.duration = 800L

            animatorSet.doOnEnd { splashScreenView.remove() }

            animatorSet.start()
        }
    }
}