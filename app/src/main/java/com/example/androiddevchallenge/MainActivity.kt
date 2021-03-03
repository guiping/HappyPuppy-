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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.androiddevchallenge.entity.PuppyEntity
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {

                }
            }
        }
    lateinit var activity: Activity;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        setContent {
            MyTheme {
                MyApp(onClick = {
                    Intent(this, DogDetailInfoActivity::class.java).apply {
                        putExtra("SELECTED_DOG", it)
                        startForResult.launch(this)
                    }
                })
            }
        }
    }
}


val puppyImg = intArrayOf(
    R.mipmap.puppy01,
    R.mipmap.puppy02,
    R.mipmap.puppy03,
    R.mipmap.puppy04,
    R.mipmap.puppy05,
    R.mipmap.puppy06,
    R.mipmap.puppy07,
    R.mipmap.puppy08,
    R.mipmap.puppy09,
    R.mipmap.puppy10,
    R.mipmap.puppy11,
    R.mipmap.puppy12,
    R.mipmap.puppy13,
    R.mipmap.puppy14,
    R.mipmap.puppy15,
    R.mipmap.puppy16,
    R.mipmap.puppy17,
    R.mipmap.puppy18
)

@Composable
fun MyApp(onClick: (puppy: PuppyEntity) -> Unit) {
    val puppyName = stringArrayResource(id = R.array.puppy_name)
    val puppyInfo = stringArrayResource(id = R.array.puppy_info)
    val puppyList = mutableListOf<PuppyEntity>()  //创建一个可变数组

    var puppyId: Int = 0
    for (imgId in puppyImg) {
        val itemPuppy = PuppyEntity()
        itemPuppy.puppyId = puppyId
        puppyId++
        itemPuppy.puppyImg = imgId
        puppyList.add(itemPuppy)
    }
    for ((index, puppyEntity) in puppyList.withIndex()) {
        puppyEntity.nickName = puppyName[index]
        puppyEntity.puppyMsg = puppyInfo[index]
    }
    println(puppyList.toString())
    puppyList(puppyList, onClick)
}


@Composable
fun puppyList(
    puppyList: List<PuppyEntity>,
    onClick: (puppy: PuppyEntity) -> Unit
) {
    LazyColumn {
        items(puppyList) { itemPuppy ->
            puppyItemCard(itemPuppy, onClick)
        }
    }
}


@Composable
fun puppyItemCard(
    puppyEntity: PuppyEntity,
    onClick: (puppy: PuppyEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick(puppyEntity) }
            .padding(6.dp),
        contentAlignment = Alignment.BottomEnd
    ) {

        val imageModifier = Modifier
            .height(136.dp)
            .fillMaxWidth()
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(id = puppyEntity.puppyImg),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(6.dp))
        Surface(
            color = Color.Shadow,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = puppyEntity.nickName,
                    style = typography.h6,
                    color = Color.White, fontSize = 16.sp
                )
                Text(
                    text = puppyEntity.puppyMsg,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = typography.body1,
                    color = Color.White, fontSize = 12.sp
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(onClick = {})
    }
}

