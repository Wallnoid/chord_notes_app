package com.chord_notes_app.ui.components

import NotesCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chord_notes_app.data.GroupsResponse


@Composable
fun GroupsTargetContainer(
    group : GroupsResponse,
    navController: NavController
) {

        Column(
            modifier = Modifier
                .fillMaxWidth()



        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                CustomIconButton(
                    icon = Icons.Outlined.Settings,
                    {
                        navController.navigate("createeditgroup/${group.id}")
                    }
                )

            }
            Spacer(modifier = Modifier.height(20.dp))


            if(group.songs.isEmpty()){

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "No songs",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.outline
                    )

                }


            }

            group.songs.forEach(
                {
                    song ->

                    Spacer(modifier = Modifier.height(10.dp))

                    NotesCard(song = song, navController = navController, {
                        navController.navigate("seeSong/${song.id}")

                    })

                }
            )


        }


}

@Preview(showBackground = true)
@Composable
fun GroupsTargetContainerPreview() {
}