package com.example.wheeliee.presentation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.wheeliee.R
import com.example.wheeliee.presentation.navigation.Screen
import com.example.wheeliee.util.ResData
import com.example.wheeliee.util.Util

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    context: Context = LocalContext.current
) {

    val openDialog = remember {
        mutableStateOf(false)
    }
    val (location, onLocationChange) = remember {
        mutableStateOf("")
    }
    val invalidLocation = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color(0xFF00008B)) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    AsyncImage(
                        model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWprvSlCq5DXnK6_Ex6h16cxNtprfjL1x-BA&usqp=CAU",
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                }
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            navHostController.navigateUp()
                        },
                    tint = Color.LightGray
                )
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            context.startActivity(Intent.createChooser(Util.emailIntent, null))
                        },
                    tint = Color.LightGray
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = "Location")
                },
                icon = {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                },
                onClick = {
                    openDialog.value = true
                },
                backgroundColor = Color(0xFF00008B),
                contentColor = Color.White
            )
        }
    ) {

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = { Text(text = "Your Location") },
                text = {
                    TextField(
                        value = location,
                        onValueChange = onLocationChange,
                        isError = invalidLocation.value
                    )
                },
                buttons = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                if (location.isNotBlank() && ResData.resData()
                                        .contains(location.capitalize())
                                ) {
                                    openDialog.value = false
                                    navHostController.navigate(
                                        Screen.LocationInfoScreen.route + "/${location.capitalize()}"
                                    )
                                } else {
                                    invalidLocation.value = true
                                    Toast.makeText(
                                        context,
                                        "Location Not Found!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth(.5f)
                        ) {
                            Text(text = "Submit")
                        }
                    }
                }
            )
        }

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.home_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Our Aim of the project is to create awareness among people about the challenges faced by the differently abled community due to the structural differences of the places which makes it's hardly accessible by the differently abled people.\n" +
                            "Through our Android application, we display all the hotels that provides easy access for the physically differently abled people and all the services provided by the hotels for the easy access of the physically differently abled people .\n" +
                            "We also provide all the customer review for each of the hotels which makes it even more reliable and gives quick understanding about the services provided by the hotel.",
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}