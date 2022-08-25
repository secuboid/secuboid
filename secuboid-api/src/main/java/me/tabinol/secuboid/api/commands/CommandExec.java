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
package me.tabinol.secuboid.api.commands;

import me.tabinol.secuboid.api.players.CommandSenderInfo;

/**
 * If you create a command for Secuboid, you should implement this interface and
 * add CommandRegistered annotation. Don't forget to add your class in
 * secuboid-plugin.yml. You have a choice of three constructors: Secuboid,
 * YourPlugin, Secuboid and YourPlugin. YourPlugin is the class of your own
 * plugin that extends JavaPlugin.
 * 
 * <pre>
 * public MyCommandExec(Secuboid secuboid) {
 *     this.secuboid = secuboid;
 * }
 *
 * // or
 * 
 * public MyCommandExec(MyPlugin myPlugin) {
 *     this.myPlugin = myPlugin;
 * }
 *
 * // or
 * 
 * public MyCommandExec(Secuboid secuboid, MyPlugin myPlugin) {
 *     this.secuboid = secuboid;
 *     this.myPlugin = myPlugin;
 * }
 * </pre>
 * 
 * Any other constructors will be ignored.
 * 
 * @see me.tabinol.secuboid.api.reflection.CommandRegistered
 */
public interface CommandExec {

    /**
     * Where the command is executed.
     * 
     * @param commandSenderInfo the command sender secuboid information (player or
     *                          console)
     * @param subArgs           the argument array (excude the command itself)
     */
    void commandExec(CommandSenderInfo commandSenderInfo, String[] subArgs);
}
