
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AudioFile
import androidx.compose.material.icons.outlined.AutoFixHigh
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chord_notes_app.data.SongsResponse
import com.chord_notes_app.ui.components.SimpleChip

@Composable
fun NotesCard(
    song: SongsResponse
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {

            }

    ) {

        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( horizontal = 16.dp, vertical = 8.dp)

            ) {

                Text(
                    text = song.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,

                    )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Outlined.AudioFile,
                    contentDescription = null,

                    modifier = Modifier
                        .padding(0.dp)

                )


            }

            Spacer(modifier = Modifier.weight(1f))




            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( horizontal = 16.dp, vertical = 8.dp)

            ) {


                Text(
                    text = song.author,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                )


                Spacer(modifier = Modifier.weight(1f))
                SimpleChip(text = song.key, icon = Icons.Outlined.MusicNote )

                SimpleChip(text = "${song.bpm} bpm", icon = Icons.Outlined.AutoFixHigh )


            }








        }


    }
}


@Preview(showBackground = true)
@Composable
fun MyComposablePreview() {
//    NotesCard()

}