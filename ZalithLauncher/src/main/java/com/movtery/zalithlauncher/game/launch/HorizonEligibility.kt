/*
 * Zalith Launcher 2
 * Copyright (C) 2025-2026 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.movtery.zalithlauncher.game.launch

import android.os.Build
import com.movtery.zalithlauncher.game.version.installed.Version
import com.movtery.zalithlauncher.game.version.installed.VersionType
import java.io.File

/**
 * Conservative Horizon eligibility checks. These checks do not start the engine;
 * launch wiring stays gated until a verified native launch adapter is available.
 */
object HorizonEligibility {
    const val SUPPORTED_MINECRAFT_VERSION = "26.3"

    fun reasonUnsupported(version: Version): String? {
        if ("arm64-v8a" !in Build.SUPPORTED_ABIS) {
            return "Horizon currently supports Android arm64-v8a devices only."
        }
        val minecraftVersion = version.getVersionInfo()?.minecraftVersion
        if (minecraftVersion != SUPPORTED_MINECRAFT_VERSION) {
            return "Horizon supports vanilla Minecraft $SUPPORTED_MINECRAFT_VERSION only."
        }
        if (version.versionType != VersionType.VANILLA) {
            return "Horizon does not support mod loaders or Java mods. Select Java (default) for this instance."
        }
        val modsDir = File(version.getGameDir(), "mods")
        if (modsDir.listFiles()?.any { it.isFile && it.extension.equals("jar", ignoreCase = true) } == true) {
            return "Horizon does not load Java mods. Remove the mods or select Java (default)."
        }
        return null
    }
}
