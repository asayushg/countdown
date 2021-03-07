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
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun TimeSetUnit(
    onUpClick: () -> Unit,
    onDownClick: () -> Unit,
    amount: String,
    isEdit: Boolean,
) {

    ConstraintLayout(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentWidth()
            .background(color = MaterialTheme.colors.surface),
    ) {

        val (up, amountText, down) = createRefs()

        IconButton(
            onClick = { onUpClick() },
            enabled = isEdit,
            modifier = Modifier.constrainAs(up) {
                start.linkTo(amountText.start)
                end.linkTo(amountText.end)
                bottom.linkTo(amountText.top)
                top.linkTo(parent.top)
            }
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_up),
                contentDescription = "Increase",
                tint = MaterialTheme.colors.onSurface,
            )
        }

        Box(
            modifier = Modifier
                .animateContentSize()
                .constrainAs(amountText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(up.bottom)
                    bottom.linkTo(down.top)
                }
        ) {
            Text(
                text = amount,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )
        }

        IconButton(
            onClick = { onDownClick() },
            enabled = isEdit,
            modifier = Modifier.constrainAs(down) {
                start.linkTo(amountText.start)
                end.linkTo(amountText.end)
                top.linkTo(amountText.bottom)
                bottom.linkTo(parent.bottom)
            }
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_down),
                contentDescription = "Decrease",
                tint = MaterialTheme.colors.onSurface,
            )
        }
    }
}
