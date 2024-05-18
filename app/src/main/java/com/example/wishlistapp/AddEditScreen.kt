package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ValueElement
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wishlistapp.data.Wish

@Composable
fun AddEditScreen( id:Long,
                   navcontroller: NavController,
                   viewmodel: WishViewModel){

    var wishTitle by remember { mutableStateOf(viewmodel.wishtitle) }
    var wishDescription by remember { mutableStateOf(viewmodel.wishdescription) }

    val scope = rememberCoroutineScope()
    val Scaffoldstate = rememberScaffoldState()
    if(id!=0L){
        val wish = viewmodel.getwishbyid(id).collectAsState(initial = Wish(0L,"",""))
        viewmodel.wishtitle=wish.value.wish
        viewmodel.wishdescription=wish.value.wishdiscription
    }

    Scaffold (
        topBar = {Appbarview(title = if(id==0L){
            stringResource(id = R.string.Add)}      //USING Stringresources(strigns.xml). WE CAN DO IT NORMALLY TO
            else{
                stringResource(id = R.string.Edit)},
            {navcontroller.navigateUp()})}) //navigateUp allows you us to navigate to the preveous screen
    {
        Column(
            Modifier
                .padding(it)
                .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
        Spacer(modifier = Modifier.height(10.dp))
        wishtextfield(label = "Title",
            Value = wishTitle,
            onvaluechanged = {
                viewmodel.updateTitle(it)})
            Spacer(modifier = Modifier.height(10.dp))
            wishtextfield(label = "Description",
                Value = wishDescription,
                onvaluechanged = {
                    viewmodel.updateDesription(it)})
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {if(viewmodel.wishtitle.isNotEmpty() &&
                viewmodel.wishdescription.isNotEmpty()){
                if(id==0L){
                    viewmodel.addwish(Wish(wish = viewmodel.wishtitle,
                        wishdiscription = viewmodel.wishdescription))
                }
                else{
                    viewmodel.updateWish(Wish(
                        id=id,
                        wish=wishTitle,
                        wishdiscription = wishDescription
                    ))
                }

            }
            else{

            }
                navcontroller.navigateUp()
            }) {
                Text(text = if(id==0L){
                    stringResource(id = R.string.Add)}      //USING Stringresources(strigns.xml). WE CAN DO IT NORMALLY TO
                else{
                    stringResource(id = R.string.Edit)}
                )
            }
        }
    }

}

//Making your own textfield
//KeyboardOptions can be used for determining type of keyboard

@Composable
fun wishtextfield(
                  label:String,
                  Value:String,
                  onvaluechanged:(String) -> Unit)
{
    OutlinedTextField(value = Value,
        modifier = Modifier.fillMaxWidth() , //MAKE SURE YOU IMPORT THE RIGHT OUTLINEDTEXTFIELD
        onValueChange = onvaluechanged,
        label = { Text(text = label)},
        keyboardOptions = (KeyboardOptions(keyboardType = KeyboardType.Text)))
}