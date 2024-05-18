package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

//ALL REPOSITORY DOES IS ADD AN ADDITIONAL LAYER ON THE DAO

class WishRepositery(private val wishDAO: WishDAO) {

    //We need to use all these functions inside a coroutine scope

    fun getAllWishes(): Flow<List<Wish>>{
        return wishDAO.getAllWishes()
    }

    suspend fun InsertWish(wish:Wish){
        wishDAO.InsertWish(wish)
    }

    suspend fun updateWish(wish:Wish){
        wishDAO.updateWish(wish)
    }

    suspend fun deleteWish(wish:Wish){
        wishDAO.deleteWish(wish)
    }

    fun getWishWithId(Id:Long):Flow<Wish>{
        return wishDAO.getWishWithId(Id)
    }
}