package com.meet.composemviapicall.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CircularProgressIndicator()
        Text(text = "Loading")
    }
}