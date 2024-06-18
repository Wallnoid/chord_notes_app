@file:OptIn( ExperimentalMaterial3Api::class)

package com.chord_notes_app.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(scrollBehavior: TopAppBarScrollBehavior, title: String,
                    icon: ImageVector,
                    onNavigationIconClicked: () -> Unit,

                    action: @Composable () -> Unit= { }

                    ) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,

        ),
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall

            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigationIconClicked()

            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Localized description"
                )
            }
        },

        actions = {
            action()

        },

        scrollBehavior = scrollBehavior,

        modifier = Modifier.shadow(elevation = 3.dp)
    )




}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {



}
