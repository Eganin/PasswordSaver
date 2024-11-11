package org.saver.project.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.saver.project.data.model.SavedPasswordDBModel

const val DB_VERSION = 1

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SavedPasswordsDatabase> {
    override fun initialize(): SavedPasswordsDatabase
}

@Database(
    entities = [SavedPasswordDBModel::class],
    version = DB_VERSION,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SavedPasswordsDatabase : RoomDatabase() {
    abstract fun savedPasswordsDao(): SavedPasswordsDao
}