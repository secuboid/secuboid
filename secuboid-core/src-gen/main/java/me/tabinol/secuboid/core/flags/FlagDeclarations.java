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
package me.tabinol.secuboid.core.flags;

import me.tabinol.secuboid.api.flagtypes.FlagType;
import me.tabinol.secuboid.api.reflection.FlagRegistered;

public class FlagDeclarations {

    private FlagDeclarations() {
    }

    @FlagRegistered
    public static final FlagType FLAG_BUILD = new FlagType("build", "Build and destroy", true, false, false, false);

    @FlagRegistered
    public static final FlagType FLAG_LAND_CREATE = new FlagType("land-create", "Create a land", true, false, false, false);
}
