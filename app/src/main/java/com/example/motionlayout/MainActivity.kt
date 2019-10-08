package com.example.motionlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.onboarding_view.*

class MainActivity : AppCompatActivity() {

    private val adapter = OnboardingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = OnboardingPage
            .values()
            .map { onboardingPageData ->
                val pageView = OnboardingPageView(this)
                pageView.setPageData(onboardingPageData)

                pageView
            }

        adapter.setData(data)
        onboardingView.setAdapter(adapter)

        nextButton.setOnClickListener {
            startActivity(Intent(this, TransitionActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }
    }
}
