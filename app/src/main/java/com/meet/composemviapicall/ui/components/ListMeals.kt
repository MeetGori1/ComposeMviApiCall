package com.meet.composemviapicall.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.meet.composemviapicall.data.model.Meals

@Composable
fun ListMeals(data: List<Meals>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(data.size) { index ->
            val meal = data[index]
            MealItem(item = meal)

        }
    }
}

@Composable
fun MealItem(item: Meals, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (item.strMealThumb.isNullOrEmpty().not()) {
                AsyncImage(
                    model = item.strMealThumb,
                    contentDescription = "thumb",
                    modifier = modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = rememberImagePainter(data = item.strMealThumb),
                    error = rememberImagePainter(data = item.strMealThumb)
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = item.strMeal ?: "",
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = item.strCategory ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = item.strArea ?: "",
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = item.strInstructions ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column {
                    Text(
                        text = "Ingredients",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = getIngredients(item),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                    }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "expand",
                    modifier = modifier.align(Alignment.End)
                )
            }
        }
    }

}

fun getIngredients(meal: Meals): String {
    var ingredients = ""
    with(meal) {
        if (strIngredient1.isNullOrEmpty().not()) ingredients += "$strIngredient1-$strMeasure1"
        if (strIngredient2.isNullOrEmpty().not()) ingredients += "$strIngredient2-$strMeasure2"
        if (strIngredient3.isNullOrEmpty().not()) ingredients += "$strIngredient3-$strMeasure3"
        if (strIngredient4.isNullOrEmpty().not()) ingredients += "$strIngredient4-$strMeasure4"
        if (strIngredient5.isNullOrEmpty().not()) ingredients += "$strIngredient5-$strMeasure5"
        if (strIngredient6.isNullOrEmpty().not()) ingredients += "$strIngredient6-$strMeasure6"
        if (strIngredient7.isNullOrEmpty().not()) ingredients += "$strIngredient7-$strMeasure7"
        if (strIngredient8.isNullOrEmpty().not()) ingredients += "$strIngredient8-$strMeasure8"
        if (strIngredient9.isNullOrEmpty().not()) ingredients += "$strIngredient9-$strMeasure9"
        if (strIngredient10.isNullOrEmpty().not()) ingredients += "$strIngredient10-$strMeasure10"
        if (strIngredient11.isNullOrEmpty().not()) ingredients += "$strIngredient11-$strMeasure11"
        if (strIngredient12.isNullOrEmpty().not()) ingredients += "$strIngredient12-$strMeasure12"
        if (strIngredient13.isNullOrEmpty().not()) ingredients += "$strIngredient13-$strMeasure13"
        if (strIngredient14.isNullOrEmpty().not()) ingredients += "$strIngredient14-$strMeasure14"
        if (strIngredient15.isNullOrEmpty().not()) ingredients += "$strIngredient15-$strMeasure15"
    }
    return ingredients.trimEnd('\n')
}