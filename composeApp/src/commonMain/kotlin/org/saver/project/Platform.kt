package org.saver.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform