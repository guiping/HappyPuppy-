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

import android.content.Intent
import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.androiddevchallenge.entity.PuppyEntity

class DogDetailInfoActivity : AppCompatActivity() {
    private lateinit var selectedDog: PuppyEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dog = intent.getSerializableExtra("SELECTED_DOG")
        selectedDog = dog as PuppyEntity
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = selectedDog.nickName
                            )
                        },
                        backgroundColor = Color.Transparent, elevation = 0.dp,
                        navigationIcon = {
                            IconButton(onClick = { navigateBack() }) {
                                val backIcon: Painter = painterResource(R.drawable.ic_back)
                                Icon(painter = backIcon, contentDescription = "ic_back")
                            }
                        }
                    )
                }
            ) {

                puppyInfo1(dog as PuppyEntity)
            }
        }
    }

    override fun onBackPressed() {
        navigateBack()
    }

    private fun navigateBack() {
        finish()
    }
}

@Composable
fun puppyInfo1(puppyEntity: PuppyEntity) {
    val typography = MaterialTheme.typography
    val stateDog by remember { mutableStateOf(puppyEntity) }
    Column {
        val imageModifier = Modifier
            .wrapContentHeight()
            .padding(10.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(id = puppyEntity.puppyImg),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier.requiredHeight(18.dp)
        )
        AdoptButton(
            adopted = stateDog.adopted
        )
        Spacer(
            modifier = Modifier.requiredHeight(18.dp)
        )
        Text(
            text = puppyEntity.puppyMsg,
            overflow = TextOverflow.Ellipsis,
            style = typography.body1
        )
    }
    if (showConfirmDialog) {
        AdoptConfirmDialog(puppyEntity = stateDog)
    }
}

@Composable
fun AdoptButton(adopted: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,

        ) {
        Button(
            onClick = { showConfirmDialog = true },
            enabled = !adopted
        ) {
            Text(text = if (adopted) "Adopted" else "Adopt")
        }
    }
}

var showConfirmDialog by mutableStateOf(false)

@Composable
fun AdoptConfirmDialog(puppyEntity: PuppyEntity) {
    AlertDialog(
        onDismissRequest = {
            showConfirmDialog = false
        },
        text = {
            Text(
                text = "Do you want to adopt this lovely dog?",
                style = MaterialTheme.typography.body2
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    showConfirmDialog = false
                    puppyEntity.adopted = true
                }
            ) {
                Text(
                    text = "Yes"
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showConfirmDialog = false
                }
            ) {
                Text(
                    text = "No"
                )
            }
        }
    )
}

@Composable
fun Greeting1(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun PreviewGreeting() {
    Greeting1(name = "nihao shijie")
}