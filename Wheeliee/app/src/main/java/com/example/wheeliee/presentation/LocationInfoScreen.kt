package com.example.wheeliee.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wheeliee.presentation.navigation.Screen
import com.example.wheeliee.util.ResData

@Composable
fun LocationInfoScreen(
    location: String,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF00008B),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        navHostController.navigateUp()
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Restaurants in $location",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 16.dp)) {
            val resData = ResData.resData()[location] ?: listOf()
            items(resData) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    elevation = 4.dp,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .clickable {
                            navHostController.navigate(
                                Screen.RestaurantDetailsScreen.route +
                                        "/${location}" +
                                        "/${resData.indexOf(it)}"
                            )
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = it.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.address,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}