package com.meet.composemviapicall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.meet.composemviapicall.ui.screens.HomeScreen
import com.meet.composemviapicall.ui.theme.ComposeMviApiCallTheme
import com.meet.composemviapicall.ui.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMviApiCallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(recipeViewModel = viewModel,modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
