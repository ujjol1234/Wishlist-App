package com.example.wishlistapp

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

//MAKE SURE YOU HAVE THE CORRECT COMPOSE DEPENDENCIES FOR TOPAPPBAT

@Composable
fun Appbarview(title:String,
               onBackNavClicked: () -> Unit = {}){
    androidx.compose.material.TopAppBar(title = {
        Text(text = title, color = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp))
    },
        elevation=3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
            navigationIcon={
                if (title != "WishList") {
                    IconButton(onClick = { onBackNavClicked() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }
            }

        )
}