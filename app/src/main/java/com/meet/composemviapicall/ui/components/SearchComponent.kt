package com.meet.composemviapicall.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(onSearchClick: (String) -> Unit, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                if (query.isNotEmpty()) {
                    errorMessage = ""
                }
            },
            isError = errorMessage.isNotEmpty(),
            label = { Text("Search") },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        if (query.isEmpty()) {
                            errorMessage = "Please enter a valid query"
                        } else {
                            errorMessage = ""
                            onSearchClick(query)
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        if (errorMessage.isNotBlank()) {
            Text(text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}