package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepositery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(val wishrepositery:WishRepositery = Graph.wishRepositery):ViewModel() {

    var wishtitle by mutableStateOf("")
    var wishdescription by mutableStateOf("")

    fun updateTitle(newString : String){
        wishtitle=newString
    }

    fun updateDesription(newString : String){
        wishdescription=newString
    }

    lateinit var getallwishes: Flow<List<Wish>>  //lateinit basically means that we are promising the compiler that this variable is
                                                // not initialized yet so we are going to initialize it later.

    init {
        viewModelScope.launch {
            getallwishes = wishrepositery.getAllWishes()
        }
    }

    fun addwish(wish:Wish){
        viewModelScope.launch (Dispatchers.IO){//Dispatcher is just a way to make it more efficient
            wishrepositery.InsertWish(wish)
        }
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishrepositery.updateWish(wish)
        }
    }

    fun deletewish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishrepositery.deleteWish(wish)
        }
    }

    fun getwishbyid(Id:Long):Flow<Wish>{
             return wishrepositery.getWishWithId(Id)
    }



}