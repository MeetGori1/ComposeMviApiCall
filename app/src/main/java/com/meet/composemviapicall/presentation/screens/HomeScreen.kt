package com.meet.composemviapicall.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.meet.composemviapicall.presentation.components.ErrorComponent
import com.meet.composemviapicall.presentation.components.LoadingComponent
import com.meet.composemviapicall.presentation.components.SuccessComponent
import com.meet.composemviapicall.presentation.viewmodel.Intents
import com.meet.composemviapicall.presentation.viewmodel.PhotosState
import com.meet.composemviapicall.presentation.viewmodel.PhotosViewModel

@Composable
fun HomeScreen(recipeViewModel: PhotosViewModel, modifier: Modifier = Modifier) {
    when (val state = recipeViewModel.state.collectAsState().value) {
        is PhotosState.Loading -> LoadingComponent(modifier)

        is PhotosState.Error -> {
            ErrorComponent(message = state.message, modifier,onRetry = {
                recipeViewModel.processIntent(Intents.GetRandomPhotos)
            })
        }

        is PhotosState.Success -> {
            val recipeList = state.photos
            SuccessComponent(recipeList,modifier=modifier ,onSearchClick = {query->
                run {
                   recipeViewModel.processIntent(
                        Intents.GetSearchedPhotos(query=query)
                    )
                }
            })
        }

    }
    LaunchedEffect(key1 = true) {
        recipeViewModel.processIntent(Intents.GetRandomPhotos)
    }
}