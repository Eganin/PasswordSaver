package org.saver.project.core.platform

import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import java.awt.Desktop

actual class PlatformConfiguration(val desktop: Desktop, val lifecycle: LifecycleRegistry)