/*
 Secuboid: Lands and Protection plugin for Minecraft server
 Copyright (C) 2014 Tabinol

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.tabinol.secuboid.core.listeners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.tabinol.secuboid.core.SecuboidImpl;

public class Listeners {

    public void register() {
        PlayerConnectionListener playerConnectionListener = new PlayerConnectionListener();
        PlayerMoveListener playerMoveListener = new PlayerMoveListener();
        SecuboidToolListener secuboidToolListener = new SecuboidToolListener();

        PluginManager pluginManager = SecuboidImpl.getPluginManager();
        JavaPlugin javaPlugin = SecuboidImpl.getJavaPLugin();
        pluginManager.registerEvents(playerConnectionListener, javaPlugin);
        pluginManager.registerEvents(playerMoveListener, javaPlugin);
        pluginManager.registerEvents(secuboidToolListener, javaPlugin);
    }
}
