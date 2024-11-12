package org.saver.project.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.saver.project.core.platform.PlatformConfiguration
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class RoomDbFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual fun getDatabase(): SavedPasswordsDatabase {
        return getDatabaseBuilder().build()
    }

    private fun getDatabaseBuilder(): RoomDatabase.Builder<SavedPasswordsDatabase> {
        val dbFilePath = documentDirectory() + "/my_room.db"
        return Room.databaseBuilder<SavedPasswordsDatabase>(
            name = dbFilePath,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}