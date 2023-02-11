package io.nguyenhuynhdev.admin.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Document::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun documentDao(): DocumentDao
}