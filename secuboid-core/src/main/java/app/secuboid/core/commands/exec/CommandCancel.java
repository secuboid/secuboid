/*
 *  Secuboid: Lands and Protection plugin for Minecraft server
 *  Copyright (C) 2014 Tabinol
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package app.secuboid.core.commands.exec;

import app.secuboid.api.Secuboid;
import app.secuboid.api.SecuboidCorePlugin;
import app.secuboid.api.commands.CommandExec;
import app.secuboid.api.messages.MessageType;
import app.secuboid.api.players.CommandSenderInfo;
import app.secuboid.api.reflection.CommandRegistered;
import app.secuboid.core.messages.Message;
import app.secuboid.core.messages.MessagePaths;
import app.secuboid.core.players.CommandSenderInfoImpl;
import app.secuboid.core.selection.SenderSelection;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@CommandRegistered( //
        pluginClass = SecuboidCorePlugin.class, //
        name = "cancel" //
)
public class CommandCancel implements CommandExec {

    Secuboid secuboid;

    public CommandCancel(Secuboid secuboid) {
        this.secuboid = secuboid;
    }

    @Override
    public void commandExec(@NotNull CommandSenderInfo commandSenderInfo, String[] subArgs) {
        // TODO cancel command

        SenderSelection senderSelection = ((CommandSenderInfoImpl) commandSenderInfo).getSelection();
        CommandSender sender = commandSenderInfo.getSender();

        if (senderSelection.removeSelection()) {
            Message.message().sendMessage(sender, MessageType.NORMAL, MessagePaths.selectionCancel());
        } else {
            Message.message().sendMessage(sender, MessageType.ERROR, MessagePaths.selectionEmpty());
        }
    }
}
