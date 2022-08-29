package com.example.wheeliee.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.wheeliee.presentation.navigation.Screen

@Composable
fun SignInScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00008B)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Wheeliee",
            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        Button(
            onClick = {
                navHostController.navigate(Screen.HomeScreen.route)
            },
            modifier = Modifier.fillMaxWidth(.6f),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Login", color = Color(0xFF00008B))
        }
    }
}