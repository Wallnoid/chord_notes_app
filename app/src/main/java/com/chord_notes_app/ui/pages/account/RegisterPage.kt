
package com.chord_notes_app.ui.pages.account

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chord_notes_app.R
import com.chord_notes_app.routes.Routes
import com.chord_notes_app.ui.components.CustomButton
import com.chord_notes_app.ui.components.CustomTextButton
import com.chord_notes_app.ui.pages.account.actions.register
import com.chord_notes_app.ui.pages.account.components.CustomTextField
import com.chord_notes_app.ui.pages.account.components.PasswordTextField
import com.chord_notes_app.ui.viewModels.AuthViewModel

@Composable
fun RegisterPage(navController: NavController,
                 authViewModel: AuthViewModel = hiltViewModel(),
                 ){

    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }



    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.fondo4),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().blur(
                radius = 30.dp
            )
        )

        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(
                    0.dp
                )
                .background(Color.Transparent)
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .background(Color.Transparent)
            ) {

                IconButton(onClick = {
                    navController.popBackStack()
                },
                    modifier = Modifier.padding(0.dp)
                        .background(
                            Color.Transparent
                        )

                    

                    ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "",
                        tint = Color(0xFF1E1A20)

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))


                Text(text = "Sign Up ", style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1A20)

                ))

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "create your account and get ready to write songs!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1A20)
                    )
                )

            }

            Spacer(modifier = Modifier.height(40.dp))


            Box(
                modifier = Modifier.clip( RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ))

            ) {
                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.background
                        ),


                    ) {

                    Spacer(modifier = Modifier.height(40.dp))


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp)


                    ) {


                        Column(
                            Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {


                            CustomTextField(
                                label = "User Name",
                                icon = Icons.Outlined.Person,
                                placeholder = "Enter your Name",
                                isValid = true,
                                errorMessage = "Error",
                                text = usernameState.value,
                                onChange = { usernameState.value = it }

                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            CustomTextField(
                                label = "Email address",
                                icon = Icons.Outlined.Email,
                                placeholder = "Enter your Email",
                                isValid = true,
                                errorMessage = "Error",
                                text = emailState.value,
                                onChange = { emailState.value = it }
                            )

                            Spacer(modifier = Modifier.height(30.dp))


                            PasswordTextField(label = "", icon = Icons.Outlined.Info,
                                    isValid = true,
                                errorMessage = "Error",
                                text = passwordState.value,
                                onChange = { passwordState.value = it }
                            )


                        }

                    }

                    Spacer(modifier = Modifier.height(50.dp))


                    CustomButton(onClick = {
                                           register(
                                                  navController = navController,
                                                  authViewModel = authViewModel,
                                                  usernameState = usernameState.value,
                                                  passwordState = passwordState.value,
                                                  emailState = emailState.value,
                                                    context = navController.context
                                           )
                                           }, label = "Sign Up", 40)



                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "already have an account ?",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.outline
                            )
                        )

                        CustomTextButton(onClick = {  navController.navigate(route = Routes.LogIn.screen) }, label = "Sign In")


                    }
                }
            }
        }
    }

}

