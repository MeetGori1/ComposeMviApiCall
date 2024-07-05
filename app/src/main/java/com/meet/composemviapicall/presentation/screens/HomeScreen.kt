package com.meet.composemviapicall.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.meet.composemviapicall.presentation.components.ErrorComponent
import com.meet.composemviapicall.presentation.components.LoadingComponent
import com.meet.composemviapicall.presentation.components.SuccessComponent
import com.meet.composemviapicall.presentation.components.shimmer.ShimmerListItem
import com.meet.composemviapicall.presentation.viewmodel.PhotoIntent
import com.meet.composemviapicall.presentation.viewmodel.PhotosState
import com.meet.composemviapicall.presentation.viewmodel.PhotosViewModel

@Composable
fun HomeScreen(recipeViewModel: PhotosViewModel, modifier: Modifier = Modifier) {
    LaunchedEffect(key1 = true) {
        recipeViewModel.processIntent(PhotoIntent.GetRandomPhotos)
    }

    val state = recipeViewModel.state.collectAsState().value

    ShimmerListItem(isLoading = state is PhotosState.Loading, modifier = modifier) {
        when (state) {
            is PhotosState.Error -> {
                ErrorComponent(
                    message = state.message,
                    modifier = modifier,
                    onRetry = { recipeViewModel.processIntent(PhotoIntent.GetRandomPhotos) }
                )
            }
            is PhotosState.Success -> {
                SuccessComponent(
                    state.data.collectAsLazyPagingItems(),
                    modifier = modifier,
                    onSearchClick = { query ->
                        recipeViewModel.processIntent(PhotoIntent.GetSearchedPhotos(query = query))
                    }
                )
            }
            else -> {} // Handle the else case if needed, or remove it
        }
    }
}