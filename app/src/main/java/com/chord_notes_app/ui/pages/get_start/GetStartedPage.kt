package com.chord_notes_app.ui.pages.get_start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chord_notes_app.R
import com.chord_notes_app.routes.Routes
import com.chord_notes_app.ui.components.CustomButton
import com.chord_notes_app.ui.components.CustomFilledButton
import com.chord_notes_app.ui.components.CustomTextButton
import com.chord_notes_app.ui.pages.account.RegisterPage
import com.chord_notes_app.ui.theme.AppTheme


@Composable
fun GetStartedPage(navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(40.dp)
                    )
                    .background(
                        Color.Transparent
                    )

                )
            {

                Image(
                    painter = painterResource(id = R.drawable.fondo4),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.align(Alignment.Center)

                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                Text(
                    text = "Discover your talent for songwritter  ",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold
                        , textAlign = TextAlign.Center

                    )
                )

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = "we are very happy that you are here, come in and write your songs.",
                    style = MaterialTheme.typography.bodyLarge.copy(

                         textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.outline

                    )
                )
            }

            Spacer(modifier = Modifier.height(30.dp))


                CustomFilledButton(
                    onClick = { navController.navigate(route = Routes.LogIn.screen)},
                    label = "Sign In" , padding = 10 )

            Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    onClick = { navController.navigate(route = Routes.LogUp.screen)},
                    label = "Sign Up" , 10)



        }

    }

}
