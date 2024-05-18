package com.example.wishlistapp

import android.graphics.drawable.shapes.Shape
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.obj_1
import kotlinx.coroutines.launch

@Composable
fun Homeview(navController: NavController,
             viewModel: WishViewModel){
    val context = LocalContext.current

    //Scaffold lets us use components such as an app bar,
    // floating action button, bottom navigation bar, etc.
    // It helps in setting up the basic layout of your screen.
    var scope = rememberCoroutineScope()
    Scaffold (
        topBar = { Appbarview(title = "WishList",{
            Toast.makeText(context,"NavBack button clicked",Toast.LENGTH_LONG).show() }) },
        floatingActionButton = {
            FloatingActionButton( modifier=Modifier.padding(20.dp),
                contentColor = Color.White,
                backgroundColor = colorResource(id = R.color.app_bar_color),
                onClick = {navController.navigate("Edit/${0L}")}
                ) {
                Icon(imageVector = Icons.Default.Add,
                    tint=Color.White,
                    contentDescription = null)
            }
        }
    ){
        val wishlist = viewModel.getallwishes.collectAsState(initial = listOf())  //This converts the Flow<List<Wish>>
       LazyColumn(modifier = Modifier
           .fillMaxSize()
           .padding(it)){
        items(wishlist.value){
           WishItem(wish = it,
               {    val id = it.id
                   navController.navigate("Edit/$id")})
        }
       }
    }
}

@Composable
fun WishItem(wish:Wish,
             onclick:() -> Unit){
    Card(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onclick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Text(text = wish.wish , fontWeight = FontWeight.ExtraBold)
        Text(text = wish.wishdiscription)
    }
}