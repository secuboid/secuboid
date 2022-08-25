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
package me.tabinol.secuboid.core.commands.exec;

import static me.tabinol.secuboid.core.messages.Message.message;

import me.tabinol.secuboid.api.Secuboid;
import me.tabinol.secuboid.api.SecuboidCorePlugin;
import me.tabinol.secuboid.api.commands.CommandExec;
import me.tabinol.secuboid.api.messages.MessageType;
import me.tabinol.secuboid.api.players.CommandSenderInfo;
import me.tabinol.secuboid.api.reflection.CommandRegistered;
import me.tabinol.secuboid.core.SecuboidImpl;
import me.tabinol.secuboid.core.messages.MessagePaths;

@CommandRegistered( //
        pluginClass = SecuboidCorePlugin.class, //
        name = "reload", //
        isAdminModeByPass = false, //
        permissions = "secuboid.core.reload" //
)
public class CommandReload implements CommandExec {

    Secuboid secuboid;

    public CommandReload(Secuboid secuboid) {
        this.secuboid = secuboid;
    }

    @Override
    public void commandExec(CommandSenderInfo commandSenderInfo, String[] subArgs) {
        message().broadcastMessage(MessageType.NORMAL, MessagePaths.generalPreReload());
        ((SecuboidImpl) secuboid).reload();
        message().broadcastMessage(MessageType.NORMAL, MessagePaths.generalReload());
    }
}