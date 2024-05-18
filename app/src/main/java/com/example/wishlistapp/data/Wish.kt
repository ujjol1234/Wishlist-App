package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
class Wish (
    @PrimaryKey(autoGenerate = true) //PrimaryKey needs to be unique for every object of the table.
    val id:Long = 0L,                //autoGenerate will automatically increment the unique id for every object in the table,
    @ColumnInfo("wish-title")  //ColumnInfo is the name of the column
    val wish:String = "",
    @ColumnInfo("wish-desc")
    val wishdiscription:String = ""
)


val obj_1 = listOf<Wish>(
    Wish(1,"I Phone","Latest"),
            Wish(2,"Bullet","Latest"),
    Wish(3,"Thar","Latest"),
    Wish(4,"PS5","Latest")
)
