package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


//using DAO

@Dao
abstract class WishDAO {

    @Query("Select * from `wish-table`")   //Selects everything from the table(SQL code)
    abstract fun getAllWishes():Flow<List<Wish>>  //Do not need to use suspend here because Flow is already here

    @Insert(onConflict = OnConflictStrategy.IGNORE)  //onConflict occurs when the id is same(will happen very rarely)
    abstract suspend fun InsertWish(wish:Wish)

    @Update
    abstract suspend fun updateWish(wish:Wish)

    @Delete
    abstract suspend fun deleteWish(wish:Wish)

    @Query("Select * from `wish-table` where id=:Id") // (SQL Code)
    abstract fun getWishWithId(Id:Long):Flow<Wish>
}