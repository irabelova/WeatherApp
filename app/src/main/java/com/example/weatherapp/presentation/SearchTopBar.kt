package com.example.weatherapp.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        placeholder = {
            Text(
                text = stringResource(
                    id = R.string.moscow
                )
            )
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,

            disabledTextColor = colorResource(id = R.color.hint_text),
            cursorColor = colorResource(id = R.color.hint_text),
            unfocusedLeadingIconColor = Color.White,
            focusedLeadingIconColor = Color.White,
            containerColor = colorResource(id = R.color.toolbar_background),
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(
        searchQuery = "Moscow",
        onValueChange = {}
    )
}