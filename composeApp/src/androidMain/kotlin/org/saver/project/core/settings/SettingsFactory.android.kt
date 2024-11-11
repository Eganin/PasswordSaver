package org.saver.project.core.settings

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.saver.project.core.platform.PlatformConfiguration

internal actual class SettingsFactoryImpl actual constructor(
    private val configuration: PlatformConfiguration
) : SettingsFactory {
    actual override fun make(name: String): Settings {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val encryptedSharedPreferences = EncryptedSharedPreferences.create(
            name,
            masterKeyAlias,
            configuration.androidContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return SharedPreferencesSettings(delegate = encryptedSharedPreferences)
    }
}