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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp

@Composable
fun Timer(
    progress: Float,
    millisPassed: Long,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        val color = Blue
        val handColor = Color.Red

        Canvas(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        ) {
            val width = ProgressStrokeWidth.toPx()

            val middle = Offset(size.minDimension / 2, size.minDimension / 2)

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 180f * progress,
                useCenter = false,
                topLeft = Offset(width, width),
                size = Size(size.width - width * 2, size.height - width * 2),
                style = Stroke(width = ProgressStrokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = -180f * progress,
                useCenter = false,
                topLeft = Offset(width, width),
                size = Size(size.width - width * 2, size.height - width * 2),
                style = Stroke(width = ProgressStrokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawCircle(
                color = color,
                center = middle,
                radius = size.width / 2 - width,
                style = Stroke(width = 1.dp.toPx(), cap = StrokeCap.Round)
            )

            withTransform(
                {
                    rotate(360f * millisPassed / 60000f, middle)
                }
            ) {

                drawLine(
                    strokeWidth = HandWidth.toPx(),
                    cap = StrokeCap.Round,
                    color = handColor,
                    start = Offset(size.minDimension / 2, size.minDimension / 2),
                    end = Offset(size.minDimension / 2, 12.dp.toPx())
                )
            }
        }
    }
}

private val ProgressStrokeWidth = 10.dp
private val HandWidth = 8.dp
