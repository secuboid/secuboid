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

import app.secuboid.api.SecuboidPlugin;
import app.secuboid.api.commands.CommandExec;
import app.secuboid.api.lands.WorldLand;
import app.secuboid.api.lands.areas.CuboidAreaForm;
import app.secuboid.api.players.CommandSenderInfo;
import app.secuboid.api.players.PlayerInfo;
import app.secuboid.api.reflection.CommandRegistered;
import app.secuboid.api.selection.PlayerSelection;
import app.secuboid.core.selection.PlayerSelectionImpl;
import org.jetbrains.annotations.NotNull;

@CommandRegistered(
        pluginClass = SecuboidPlugin.class,
        name = "select cuboid",
        aliases = "cub",
        allowConsole = false,
        sourceActionFlags = "land-create"
)
public class CommandSelectCuboid implements CommandExec {

    @Override
    public void commandExec(@NotNull CommandSenderInfo commandSenderInfo, @NotNull String[] subArgs) {
        PlayerInfo playerInfo = (PlayerInfo) commandSenderInfo;
        WorldLand worldLand = playerInfo.getWorldLand();
        PlayerSelection playerSelection = playerInfo.getPlayerSelection();

        ((PlayerSelectionImpl) playerSelection).createActiveSelectionModifyExpand(worldLand, CuboidAreaForm.class);
    }
}
