package com.meet.composemviapicall.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.colorspace.WhitePoint
import com.meet.composemviapicall.ui.components.ErrorComponent
import com.meet.composemviapicall.ui.components.LoadingComponent
import com.meet.composemviapicall.ui.components.SuccessComponent
import com.meet.composemviapicall.ui.viewmodel.Intents
import com.meet.composemviapicall.ui.viewmodel.RecipeState
import com.meet.composemviapicall.ui.viewmodel.RecipeViewModel

@Composable
fun HomeScreen(receipeViewModel: RecipeViewModel, modifier: Modifier = Modifier) {
    val state = receipeViewModel.state.collectAsState().value
    when (state) {
        is RecipeState.Loading -> LoadingComponent()

        is RecipeState.Error -> {
            ErrorComponent(message = state.message, onRetry = {
                receipeViewModel.processIntent(Intents.GetRandomMeals)
            })
        }

        is RecipeState.Success -> {
            val recipeList = state.meals
            SuccessComponent(recipeList, onSearchClick = {query->
                run {
                    receipeViewModel.processIntent(
                        Intents.GetSearchMeals(query)
                    )
                }
            })
        }

    }
    LaunchedEffect(key1 = true) {
        receipeViewModel.processIntent(Intents.GetRandomMeals)
    }
}