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
package app.secuboid.api.events;

import app.secuboid.api.lands.Land;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This events is called every time a player moves from a land to an other, or
 * to an other world.
 */
public class PlayerLandChangeEvent extends LandEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final Location fromLoc;
    private final Location toLoc;
    private final Land lastLand;
    private final boolean isTp;
    private boolean cancelled = false;

    /**
     * Instantiates a new player land change events.
     *
     * @param lastLand the last land
     * @param land     the actual land
     * @param player   the player
     * @param fromLoc  from location
     * @param toLoc    the to location
     * @param isTp     the is a player teleport
     */
    public PlayerLandChangeEvent(@Nullable Land lastLand, @NotNull Land land, @NotNull Player player, @Nullable Location fromLoc, @NotNull Location toLoc,
                                 boolean isTp) {
        super(land);
        this.lastLand = lastLand;
        this.player = player;
        this.fromLoc = fromLoc;
        this.toLoc = toLoc;
        this.isTp = isTp;
    }

    /**
     * Gets the player.
     *
     * @return the player
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the last land or null if it is a new player.
     *
     * @return the last land or null
     */
    @Nullable
    public Land getLastLand() {
        return lastLand;
    }

    /**
     * Gets the from location.
     *
     * @return the from location
     */
    @Nullable
    public Location getFromLoc() {
        return fromLoc;
    }

    /**
     * Gets the to location.
     *
     * @return the to location
     */
    @NotNull
    public Location getToLoc() {
        return toLoc;
    }

    /**
     * Checks if this is a player teleport.
     *
     * @return true, if it is a player teleport
     */
    public boolean isTp() {
        return isTp;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean bln) {
        cancelled = bln;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    @SuppressWarnings("java:S4144")
    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
