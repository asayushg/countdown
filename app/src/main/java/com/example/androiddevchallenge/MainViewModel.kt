/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val isTimer = mutableStateOf(false)
    val isEdit = mutableStateOf(true)
    val hour = mutableStateOf(0L)
    val min = mutableStateOf(0L)
    val sec = mutableStateOf(0L)
    val milliSeconds = mutableStateOf(0L)

    private var totalMillis = 0L
    var millisPassed = mutableStateOf(0L)

    private var timer: CountDownTimer? = null
    val donePercent = mutableStateOf(1F)

    fun reset() {
        destroyTimer()
        isTimer.value = false
        isEdit.value = true

        hour.value = 0
        min.value = 0
        sec.value = 0
        milliSeconds.value = 0L
        totalMillis = 0L
        millisPassed.value = 0L
        donePercent.value = 1f
    }

    fun pause() {
        timer?.cancel()
        timer = null
        isTimer.value = false
        isEdit.value = false
    }

    fun play() {
        if (totalMillis == 0L) totalMillis =
            60 * 60 * 1000 * hour.value + 60 * 1000 * min.value + 1000 * sec.value

        if (totalMillis != 0L) {
            startTimerWith(
                totalMillis - millisPassed.value
            )
            isTimer.value = true
            isEdit.value = false
        }
    }

    var tempMinute = 0L
    var tempSeconds = 0L
    private fun startTimerWith(millis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millis, 1) {
            override fun onTick(millisUntilFinished: Long) {
                millisPassed.value = totalMillis - millisUntilFinished
                milliSeconds.value = millisUntilFinished % 1000 // milliseconds
                tempSeconds = millisUntilFinished / 1000 // seconds + minute + hour
                tempMinute = tempSeconds / 60 // minutes+hour
                hour.value = tempMinute / 60 // hour
                min.value = tempMinute % 60
                sec.value = tempSeconds % 60
                donePercent.value = (totalMillis - millisPassed.value) / totalMillis.toFloat()
            }

            override fun onFinish() {
                reset()
            }
        }
        timer?.start()
    }

    fun destroyTimer() {
        timer?.cancel()
        timer = null
    }
}
