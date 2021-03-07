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

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.components.ControlBar
import com.example.androiddevchallenge.ui.components.TimeSetUnit
import com.example.androiddevchallenge.ui.components.Timer
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyTheme {
                MyApp(
                    viewModel.isTimer,
                    viewModel.isEdit,
                    viewModel.hour,
                    viewModel.min,
                    viewModel.sec,
                    viewModel::reset,
                    viewModel::pause,
                    viewModel::play,
                    viewModel.donePercent,
                    viewModel.millisPassed
                )
            }
        }
    }
}

// Start building your app here!
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MyApp(
    isTimer: MutableState<Boolean>,
    isEdit: MutableState<Boolean>,
    hour: MutableState<Long>,
    min: MutableState<Long>,
    sec: MutableState<Long>,
    reset: () -> Unit,
    pause: () -> Unit,
    play: () -> Unit,
    donePercent: MutableState<Float>,
    milliSeconds: MutableState<Long>,
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Log.d(
                "TAG",
                "MyApp: ${milliSeconds.value} ${donePercent.value} ${milliSeconds.value / 60000f}"
            )
            Timer(
                progress = donePercent.value,
                millisPassed = milliSeconds.value
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                TimeSetUnit(
                    onUpClick = {
                        if (hour.value == 99L) hour.value = 0 else hour.value++
                    },
                    onDownClick = {
                        if (hour.value == 0L) hour.value = 99 else hour.value--
                    },
                    amount = toAmountString(hour.value),
                    isEdit = isEdit.value
                )

                TimeSetUnit(
                    onUpClick = {
                        if (min.value == 59L) min.value = 0 else min.value++
                    },
                    onDownClick = {
                        if (min.value == 0L) min.value = 59 else min.value--
                    },
                    amount = toAmountString(min.value),
                    isEdit = isEdit.value
                )

                TimeSetUnit(
                    onUpClick = {
                        if (sec.value == 59L) sec.value = 0 else sec.value++
                    },
                    onDownClick = {
                        if (sec.value == 0L) sec.value = 59 else sec.value--
                    },
                    amount = toAmountString(sec.value),
                    isEdit = isEdit.value
                )
            }

            ControlBar(
                onPlay = {
                    play()
                },
                onPause = {
                    pause()
                },
                onStop = {
                    reset()
                },
                isTimer = isTimer.value
            )
        }
    }
}

private fun toAmountString(i: Long): String {
    return if (i < 10) "0$i" else i.toString()
}
