package com.example.wheeliee.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.wheeliee.util.ResData
import com.example.wheeliee.util.Restaurant

@Composable
fun RestaurantDetails(
    location: String,
    index: Int,
    navHostController: NavHostController
) {

    val resData = ResData.resData()[location]?.get(index) ?: Restaurant()

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
                    text = resData.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    ) {
        Column {
            AsyncImage(
                model = resData.photo,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.Low
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Address: ${resData.address}",
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Facilities:",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                resData.facilities.forEach {
                    Text(text = it)
                }
            }
        }
    }
}