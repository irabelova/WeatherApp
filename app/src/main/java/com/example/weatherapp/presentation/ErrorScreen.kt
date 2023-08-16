package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
    }
}

@Composable
fun ErrorImageScreen() {
    Image(
        modifier = Modifier.size(90.dp),
        painter = painterResource(id = R.drawable.ic_broken_image),
        contentDescription = null
    )
}

@Composable
fun ErrorSearchScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(
                id = R.string.city_was_not_found
            ),
            fontSize = 18.sp,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}

@Preview(showBackground = true)
@Composable
fun ErrorImageScreenPreview() {
    ErrorImageScreen()
}

@Preview(showBackground = true)
@Composable
fun ErrorSearchScreenPreview() {
    ErrorSearchScreen()
}