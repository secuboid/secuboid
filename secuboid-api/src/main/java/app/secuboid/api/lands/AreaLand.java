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
package app.secuboid.api.lands;

import app.secuboid.api.lands.areas.Area;
import app.secuboid.api.lands.areas.AreaForm;
import app.secuboid.api.lands.areas.AreaResult;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents a land with a number of areas.
 */
public interface AreaLand extends Land {

    /**
     * Adds the area.
     *
     * @param areaForm the area form
     * @param callback the method to call back if success or not
     */
    void addArea(@NotNull AreaForm areaForm, @Nullable Consumer<AreaResult> callback);

    /**
     * Removes the area.
     *
     * @param key      the key
     * @param callback the method to call back if success or not
     */
    void removeArea(int key, @Nullable Consumer<AreaResult> callback);

    /**
     * Removes the area.
     *
     * @param area     the area
     * @param callback the method to call back if success or not
     */
    void removeArea(@NotNull Area area, @Nullable Consumer<AreaResult> callback);

    /**
     * Replace area.
     *
     * @param key         the key
     * @param newAreaForm the new areaForm
     * @param callback    the method to call back if success or not
     */
    void replaceArea(int key, @NotNull AreaForm newAreaForm, @Nullable Consumer<AreaResult> callback);

    /**
     * Gets the area.
     *
     * @param key the key
     * @return the area
     */
    @Nullable Area getArea(int key);

    /**
     * Gets the area key.
     *
     * @param area the area
     * @return the area key or null if the area is not member of this land
     */
    @Nullable Integer getAreaKey(@NotNull Area area);

    /**
     * Gets the area keys.
     *
     * @return the area keys
     */
    @NotNull Set<Integer> getAreaKeys();

    /**
     * Gets the ids and areas map.
     *
     * @return the ids and areas map
     */
    @NotNull Map<Integer, Area> getIdToArea();

    /**
     * Gets the areas.
     *
     * @return the areas
     */
    @NotNull Collection<Area> getAreas();

    /**
     * Gets the parent. A null value means a not yet activated land or an orphan.
     *
     * @return the parent or the land is a world
     */
    @Nullable Land getParent();

    /**
     * Checks if the land is parent, grandparent or ancestor.
     *
     * @param land the land
     * @return true, if is ancestor
     */
    boolean isParentOrAncestor(@NotNull Land land);

    /**
     * Checks if is player in land. No parent verify.
     *
     * @param player the player
     * @return true, if is player in land
     */
    boolean isPlayerInLand(@NotNull Player player);
}
