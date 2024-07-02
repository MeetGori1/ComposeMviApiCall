package com.meet.composemviapicall.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meet.composemviapicall.data.model.Meals

@Composable
fun SuccessComponent(
    data: List<Meals>,
    onSearchClick: (String) -> Unit
) {
    Column() {
        Text(
            text = "Recipe Finder",
            fontWeight = FontWeight(900),
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp,
            modifier = Modifier.padding(15.dp)
        )
        SearchComponent(onSearchClick = onSearchClick)
        ListMeals(data = data)
    }
}