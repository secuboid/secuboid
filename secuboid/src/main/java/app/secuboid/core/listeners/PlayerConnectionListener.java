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
package app.secuboid.core.listeners;

import app.secuboid.api.players.PlayerInfo;
import app.secuboid.core.SecuboidImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.event.EventPriority.MONITOR;

public class PlayerConnectionListener extends AbstractListener {

    PlayerConnectionListener() {
    }

    @EventHandler(priority = MONITOR, ignoreCancelled = true)
    public void onPlayerJoinMonitor(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getPlayerInfosImpl().addPlayer(player);
    }

    @EventHandler(priority = MONITOR)
    public void onPlayerQuitMonitor(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerInfo playerInfo = getPlayerInfosImpl().getPlayerInfo(player);
        SecuboidImpl.instance().getChatGetter().remove(playerInfo);
        getPlayerInfosImpl().removePlayer(player);
    }
}
