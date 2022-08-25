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
package me.tabinol.secuboid.core.lands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import me.tabinol.secuboid.api.lands.AreaLand;
import me.tabinol.secuboid.api.lands.Land;
import me.tabinol.secuboid.api.lands.areas.Area;

public class AreaLandImpl extends LandImpl implements AreaLand {

    private final Map<Integer, Area> idToArea;

    private Land parent;

    // IMPORTANT: Please use createLand in Lands class to create a Land or it will
    // not be accessible and saved.
    public AreaLandImpl(String name, Land parent) {
        super(name);
        this.parent = parent;

        idToArea = new HashMap<>();
    }

    @Override
    public void addArea(Area area) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean removeArea(int key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeArea(Area area) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean replaceArea(int key, Area newArea) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Area getArea(int key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getAreaKey(Area area) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Integer> getAreaKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Integer, Area> getIdToArea() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Area> getAreas() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Land getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setParent(AreaLand newParent) {
        // TODO Auto-generated method stub
        // Caution : Set parent to the world if you receive a null

    }

    @Override
    public boolean isParentOrAncestor(Land land) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isPlayerInLand(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

}
