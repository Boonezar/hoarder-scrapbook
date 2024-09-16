package com.boonezar.hoarderscrapbook.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.boonezar.hoarderscrapbook.models.ImageUri
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ImageSlider(images: List<ImageUri>) {
    var currentImageIndex by remember { mutableIntStateOf(0) }
    var isAnimating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .height(100.dp)
        .fillMaxWidth()
        .padding(16.dp)) {
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            itemsIndexed(images) { index, image ->
                Card(
                    modifier = Modifier
                        .width(70.dp)
                        .height(100.dp)
                        .clickable {
                            if (index != currentImageIndex && !isAnimating) {
                                isAnimating = true
                                coroutineScope.launch {
                                    val delayMillis = 500L
                                    delay(delayMillis / 2)
                                    currentImageIndex = index
                                    delay(delayMillis)
                                    isAnimating = false
                                }
                            }
                        }
                ) {
                    AsyncImage(model = image.uri, contentDescription = "")
                }
            }
        }
    }
}