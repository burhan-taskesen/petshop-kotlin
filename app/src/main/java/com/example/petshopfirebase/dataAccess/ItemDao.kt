package com.example.petshopfirebase.dataAccess

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petshopfirebase.entities.BotItem

@Dao
interface ItemDao {
    @Query("SELECT * FROM BotItem")
    fun getAll(): List<BotItem>

    @Query("SELECT * FROM BotItem WHERE uuid IN (:itemIds)")
    fun loadAllByIds(itemIds: IntArray): List<BotItem>

    @Insert
    fun insertAll(vararg items: BotItem)

    @Delete
    fun delete(item: BotItem)
}