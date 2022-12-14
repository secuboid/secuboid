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
package app.secuboid.core.players;

import app.secuboid.api.players.CommandSenderInfo;
import app.secuboid.api.players.ConsoleCommandSenderInfo;
import app.secuboid.api.players.PlayerInfo;
import app.secuboid.api.players.PlayerInfos;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerInfosImpl implements PlayerInfos {

    private final Map<CommandSender, CommandSenderInfo> senderToInfo;

    public PlayerInfosImpl() {
        senderToInfo = new HashMap<>();
    }

    public ConsoleCommandSenderInfo addConsoleCommandSender(ConsoleCommandSender consoleCommandSender) {
        ConsoleCommandSenderInfo consoleCommandSenderInfo = new ConsoleCommandSenderInfoImpl(consoleCommandSender);
        senderToInfo.put(consoleCommandSender, consoleCommandSenderInfo);

        return consoleCommandSenderInfo;
    }

    public PlayerInfo addPlayer(Player player) {
        PlayerInfo playerInfo = new PlayerInfoImpl(player);
        senderToInfo.put(player, playerInfo);

        return playerInfo;
    }

    public void removePlayer(Player player) {
        PlayerInfo playerInfo = (PlayerInfo) senderToInfo.get(player);

        // First, remove AutoCancelSelect
        // TODO reactivate
        // ((PlayerInfoImpl) playerInfo).setAutoCancelSelect(false);

        senderToInfo.remove(player);
    }

    @Override
    public CommandSenderInfo get(CommandSender sender) {
        return senderToInfo.get(sender);
    }

    @Override
    public PlayerInfo getPlayerInfo(Player player) {
        return (PlayerInfo) senderToInfo.get(player);
    }

    @Override
    public @NotNull Collection<CommandSenderInfo> getAll() {
        return senderToInfo.values();
    }

    public void removeAll() {
        for (final CommandSenderInfo info : getAll()) {

            // TODO clear selection
            /*
             * final PlayerSelection playerSelection = entry.getSelection();
             * if (playerSelection != null && playerSelection.hasSelection()) {
             * try {
             * // Cancel selection
             * new CommandCancel(secuboid, null, entry.getPlayer(), null).commandExecute();
             * } catch (SecuboidCommandException e) {
             * secuboid.getLogger().log(Level.WARNING, String.
             * format("Unable to cancel the selection for the player [name=%s, uuid=%s]",
             * entry.getPlayer().getUniqueId(), entry.getPlayer().getName()));
             * }
             * }
             */
        }
        senderToInfo.clear();
    }
}
