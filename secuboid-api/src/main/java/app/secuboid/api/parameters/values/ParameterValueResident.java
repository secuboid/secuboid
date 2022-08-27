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
package app.secuboid.api.parameters.values;

import app.secuboid.api.exceptions.ParameterValueException;
import app.secuboid.api.lands.Land;
import app.secuboid.api.reflection.ParameterValueRegistered;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.lang.String.format;

/**
 * Represents a land resident.
 */
@ParameterValueRegistered(name = "resident", shortName = "res", chatColor = "\u00A7A", priority = 60)
public class ParameterValueResident implements ParameterValue {

    private static final String NAME = ParameterValueResident.class.getAnnotation(ParameterValueRegistered.class)
            .name();
    private static final String SHORT_NAME = ParameterValueResident.class.getAnnotation(ParameterValueRegistered.class)
            .shortName();
    private static final String CHAT_COLOR = ParameterValueResident.class.getAnnotation(ParameterValueRegistered.class)
            .chatColor();
    private static final int PRIORITY = ParameterValueResident.class.getAnnotation(ParameterValueRegistered.class)
            .priority();

    private final int level;

    private long id;

    public ParameterValueResident(int level) {
        this.level = level;
        id = ID_NON_CREATED_VALUE;
    }

    // Needed for load from database
    public static ParameterValueResident newInstance(@NotNull String value) throws ParameterValueException {
        int level;

        try {
            level = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            String msg = format("Non parseable level number for a resident [level=%s]", value);
            throw new ParameterValueException(msg, e);
        }

        return new ParameterValueResident(level);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

    @Override
    public @NotNull String getShortName() {
        return SHORT_NAME;
    }

    @Override
    public @NotNull String getChatColor() {
        return CHAT_COLOR;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public @NotNull String getValue() {
        return Integer.toString(level);
    }

    @Override
    public boolean hasAccess(@NotNull Entity entity) {
        return false;
    }

    @Override
    public boolean hasAccess(@NotNull Entity entity, @NotNull Land originLand) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                " level='" + level + "'" +
                ", id='" + getId() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ParameterValueResident parameterValueResident)) {
            return false;
        }

        return level == parameterValueResident.level && id == parameterValueResident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, id);
    }
}
