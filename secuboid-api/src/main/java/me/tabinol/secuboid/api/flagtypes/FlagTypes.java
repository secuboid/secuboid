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
package me.tabinol.secuboid.api.flagtypes;

import java.util.Set;

/**
 * Contains the store for flag types.
 */
public interface FlagTypes {

    /**
     * Gets the a flag type from the name.
     *
     * @param name the name
     * @return the flag type
     */
    FlagType getFlagType(String name);

    /**
     * Gets all flag type names.
     *
     * @return the flag type names
     */
    Set<String> getFlagTypeNames();
}