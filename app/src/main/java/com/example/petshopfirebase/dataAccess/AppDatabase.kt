package com.example.petshopfirebase.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petshopfirebase.entities.BotItem

@Database(entities = [BotItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}