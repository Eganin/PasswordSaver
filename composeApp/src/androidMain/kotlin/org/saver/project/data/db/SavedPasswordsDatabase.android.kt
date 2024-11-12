package org.saver.project.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.saver.project.core.platform.PlatformConfiguration

actual class RoomDbFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual fun getDatabase(): SavedPasswordsDatabase {
        return getDatabaseBuilder(platformConfiguration.androidContext)
            .build()
    }

    private fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SavedPasswordsDatabase> {
        val dbFile = ctx.getDatabasePath("$DATABASE_FILE_NAME.db")
        return Room.databaseBuilder<SavedPasswordsDatabase>(
            context = ctx,
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
    }
}