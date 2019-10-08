/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.motionlayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.onboarding_view.view.*

class OnboardingView : FrameLayout, ViewPager.OnPageChangeListener {

  private val numberOfPages by lazy { OnboardingPage.values().size }

  constructor(context: Context) : super(context) {
    initializeUi(context)
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    initializeUi(context)
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    initializeUi(context)
  }

  private fun initializeUi(context: Context?) {
    LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)

    setupListeners()
  }

  private fun setupListeners() {
    pagesList.addOnPageChangeListener(this)
    pageIndicator.setViewPager(pagesList)

    previousButton.setOnClickListener {
      pagesList.setCurrentItem(pagesList.currentItem - 1, true)
    }
    nextButton.setOnClickListener {
      pagesList.setCurrentItem(pagesList.currentItem + 1, true)
    }
    finishButton.setOnClickListener {
      Toast.makeText(context, "onboarding_finished", Toast.LENGTH_SHORT).show()
    }
  }

  fun setAdapter(adapter: PagerAdapter) {
    pagesList.adapter = adapter
  }

  override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    if (numberOfPages > 1) {
      val newProgress = (position + positionOffset) / (numberOfPages - 1)

      onboardingRoot.progress = newProgress
    }
  }

  override fun onPageScrollStateChanged(state: Int) = Unit
  override fun onPageSelected(position: Int) = Unit
}