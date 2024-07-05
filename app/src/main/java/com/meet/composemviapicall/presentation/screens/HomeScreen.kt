package com.meet.composemviapicall.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.meet.composemviapicall.presentation.components.ErrorComponent
import com.meet.composemviapicall.presentation.components.LoadingComponent
import com.meet.composemviapicall.presentation.components.SuccessComponent
import com.meet.composemviapicall.presentation.viewmodel.Intents
import com.meet.composemviapicall.presentation.viewmodel.PhotosState
import com.meet.composemviapicall.presentation.viewmodel.PhotosViewModel

@Composable
fun HomeScreen(recipeViewModel: PhotosViewModel, modifier: Modifier = Modifier) {
    val lazyPagingItems = recipeViewModel.photos.collectAsLazyPagingItems()

    SuccessComponent(lazyPagingItems, modifier = modifier, onSearchClick = { query ->
        recipeViewModel.processIntent(Intents.GetSearchedPhotos(query = query))
    })

    LaunchedEffect(key1 = true) {
        recipeViewModel.processIntent(Intents.GetRandomPhotos)
    }
}