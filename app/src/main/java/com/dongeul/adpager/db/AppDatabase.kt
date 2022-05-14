package com.dongeul.adpager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dongeul.adpager.model.Content

@Database(entities = [Content:: class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pagerDao() : PagerDao
}