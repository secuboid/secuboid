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
package app.secuboid.core.lands.areas;

import app.secuboid.api.lands.AreaLand;
import app.secuboid.api.lands.areas.Area;
import app.secuboid.api.lands.areas.AreaForm;
import app.secuboid.api.messages.MessagePath;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AreaImpl implements Area {

    private final AreaForm areaForm;
    private final int id;
    private final AreaLand land;

    public AreaImpl(AreaForm areaForm, int id, AreaLand land) {
        this.areaForm = areaForm;
        this.id = id;
        this.land = land;

        ((AreaFormImpl) areaForm).isResizable = false;
    }

    @Override
    public AreaForm getAreaForm() {
        return areaForm;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public @NotNull AreaLand getLand() {
        return land;
    }

    @Override
    public int getX1() {
        return areaForm.getX1();
    }

    @Override
    public int getY1() {
        return areaForm.getY1();
    }

    @Override
    public int getZ1() {
        return areaForm.getZ1();
    }

    @Override
    public int getX2() {
        return areaForm.getX2();
    }

    @Override
    public int getY2() {
        return areaForm.getY2();
    }

    @Override
    public int getZ2() {
        return areaForm.getZ2();
    }

    public boolean isLocationInside(int x, int z) {
        return areaForm.isLocationInside(x, z);
    }

    public boolean isLocationInside(int x, int y, int z) {
        return areaForm.isLocationInside(x, y, z);
    }

    public boolean isLocationInside(@NotNull Location loc) {
        return areaForm.isLocationInside(loc);
    }

    @Override
    public boolean isLocationInsideSquare(int x, int z) {
        return areaForm.isLocationInsideSquare(x, z);
    }

    public @NotNull MessagePath getMessagePath() {
        return areaForm.getMessagePath();
    }

    @Override
    public long getArea() {
        return areaForm.getArea();
    }

    @Override
    public long getVolume() {
        return areaForm.getVolume();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AreaImpl)) {
            return false;
        }
        AreaImpl areaImpl = (AreaImpl) o;
        return Objects.equals(areaForm, areaImpl.areaForm) && id == areaImpl.id && Objects.equals(land, areaImpl.land);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaForm, id, land);
    }
}
