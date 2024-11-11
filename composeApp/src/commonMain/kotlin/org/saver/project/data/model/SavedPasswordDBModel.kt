package org.saver.project.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_passwords")
data class SavedPasswordDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val login: String,
    val passwordKey: String
)