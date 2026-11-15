package com.movtery.zalithlauncher.game.launch

import com.movtery.zalithlauncher.game.version.installed.Version
import com.movtery.zalithlauncher.setting.GameSettings

enum class EngineBackend {
    JAVA,
    HORIZON
}

object EngineBackendResolver {
    fun resolve(version: Version): EngineBackend = version.getEngineBackend()

    fun isHorizonEnabled(version: Version): Boolean = resolve(version) == EngineBackend.HORIZON
}

fun Version.getEngineBackend(): EngineBackend = versionConfig.engineBackend ?: GameSettings.engineBackend.getValue()
