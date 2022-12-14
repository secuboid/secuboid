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
package app.secuboid.core.flagtypes;

import app.secuboid.api.flagtypes.FlagType;
import app.secuboid.api.flagtypes.FlagTypes;
import app.secuboid.api.reflection.FlagRegistered;
import app.secuboid.core.reflection.PluginLoader;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FlagTypesImpl implements FlagTypes {

    private final Map<String, FlagType> nameToFlagType;

    public FlagTypesImpl() {
        nameToFlagType = new HashMap<>();
    }

    public void init(PluginLoader pluginLoader) {
        if (!nameToFlagType.isEmpty()) {
            return;
        }

        Set<FlagType> flagTypes = pluginLoader.getAnnotatedConstants(FlagRegistered.class, FlagType.class);
        flagTypes.forEach(f -> nameToFlagType.put(f.name(), f));

    }

    @Override
    public FlagType getFlagType(String flagName) {
        return nameToFlagType.get(flagName);
    }

    @Override
    public @NotNull Set<String> getFlagTypeNames() {
        return nameToFlagType.keySet();
    }
}
