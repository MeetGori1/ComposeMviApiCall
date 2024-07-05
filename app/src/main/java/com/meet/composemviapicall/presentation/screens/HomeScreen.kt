package com.meet.composemviapicall.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.meet.composemviapicall.presentation.components.ErrorComponent
import com.meet.composemviapicall.presentation.components.LoadingComponent
import com.meet.composemviapicall.presentation.components.SuccessComponent
import com.meet.composemviapicall.presentation.viewmodel.Intents
import com.meet.composemviapicall.presentation.viewmodel.RecipeState
import com.meet.composemviapicall.presentation.viewmodel.RecipeViewModel

@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel, modifier: Modifier = Modifier) {
    when (val state = recipeViewModel.state.collectAsState().value) {
        is RecipeState.Loading -> LoadingComponent(modifier)

        is RecipeState.Error -> {
            ErrorComponent(message = state.message, modifier,onRetry = {
                recipeViewModel.processIntent(Intents.GetRandomMeals)
            })
        }

        is RecipeState.Success -> {
            val recipeList = state.meals
            SuccessComponent(recipeList,modifier=modifier ,onSearchClick = {query->
                run {
                 /*   recipeViewModel.processIntent(
                        Intents.GetSearchMeals()
                    )*/
                }
            })
        }

    }
    LaunchedEffect(key1 = true) {
        recipeViewModel.processIntent(Intents.GetRandomMeals)
    }
}