package org.saver.project.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedPasswordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedPassword(savedPassword: SavedPasswordDBModel)

    @Query("SELECT * FROM saved_passwords")
    suspend fun getAllSavedPasswords(): List<SavedPasswordDBModel>
}