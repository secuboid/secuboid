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

package app.secuboid.core.selection.active;

import app.secuboid.api.lands.areas.Area;
import app.secuboid.api.messages.MessageType;
import app.secuboid.core.messages.MessagePaths;
import app.secuboid.core.scoreboard.SecuboidScoreboard;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static app.secuboid.core.messages.Message.message;

class SelectionScoreboardArea extends SelectionScoreboard {

    private final @NotNull Area area;

    SelectionScoreboardArea(@NotNull Player player, @NotNull Area area) {
        super(player);
        this.area = area;
    }

    @Override
    void init() {
        String title = message().get(MessageType.TITLE, MessagePaths.selectionScoreboardAreaTitle());
        String[] lines = new String[1];
        lines[0] = message().get(MessageType.NORMAL, MessagePaths.selectionScoreboardAreaLocationPath(area));

        scoreboard = new SecuboidScoreboard(player, title, lines);
        scoreboard.init();
    }

    @Override
    void update() {
        // No update, fix selection
    }
}
