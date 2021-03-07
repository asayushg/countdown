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
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@ExperimentalAnimationApi
@Composable
fun ControlBar(
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit,
    isTimer: Boolean
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        Box {

            ControlButton(
                isTimer = !isTimer,
                onClick = { onPlay() },
                drawable = R.drawable.ic_play_arrow,
                drawableDes = "Play"
            )

            ControlButton(
                isTimer = isTimer,
                onClick = { onPause() },
                drawable = R.drawable.ic_pause,
                drawableDes = "Pause"
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        ControlButton(
            isTimer = true,
            onClick = { onStop() },
            drawable = R.drawable.ic_stop,
            drawableDes = "Stop"
        )
    }
}
