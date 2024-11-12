package org.saver.project.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.saver.project.core.platform.PlatformConfiguration
import java.io.File

actual class RoomDbFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual fun getDatabase(): SavedPasswordsDatabase {
        return getDatabaseBuilder().build()
    }

    private fun getDatabaseBuilder(): RoomDatabase.Builder<SavedPasswordsDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "$DATABASE_FILE_NAME.db")
        return Room.databaseBuilder<SavedPasswordsDatabase>(
            name = dbFile.absolutePath,
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
    }
}