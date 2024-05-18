package com.example.wishlistapp

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.data.WishDatabase
import com.example.wishlistapp.data.WishRepositery
//ADDING THIS TO THE MANIFEST IS NECESSARY FOR THE DATABASE TO WORK:-
//  android:name=".WishListApp"

object Graph {
    lateinit var database: WishDatabase

    val wishRepositery by lazy { // by lazy means that wishRepositery will be initialized only when the app needs it and not as soon as the app starts.
        WishRepositery(wishDAO = database.WishDAO())
    }

    fun provide(context: Context){
        //database initializer
        database = Room.databaseBuilder(context = context,WishDatabase::class.java,"wishlist.databse").build()
    }
}