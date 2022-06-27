package com.example.petshopfirebase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BotItem(
    @ColumnInfo(name = "name")
    var name :String = "",
    @ColumnInfo(name = "imageSource")
    var imageSource : String = "",
    @ColumnInfo(name = "price")
    var price : Double = 0.0,
    @PrimaryKey
    var uuid : String = "")
{
    override fun toString(): String {
        return "$name $price $uuid"
    }
}
