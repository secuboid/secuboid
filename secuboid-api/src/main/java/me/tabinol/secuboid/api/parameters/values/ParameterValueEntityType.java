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
package me.tabinol.secuboid.api.parameters.values;

import static java.lang.String.format;

import java.util.Objects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import me.tabinol.secuboid.api.exceptions.ParameterValueException;
import me.tabinol.secuboid.api.lands.Land;
import me.tabinol.secuboid.api.reflection.ParameterValueRegistered;

/**
 * Represents a specific entity type.
 */
@ParameterValueRegistered(name = "entity-type", shortName = "et", chatColor = "\u00A75", priority = 60)
public class ParameterValueEntityType implements ParameterValue {

    private static final String NAME = ParameterValueEntityType.class.getAnnotation(ParameterValueRegistered.class)
            .name();
    private static final String SHORT_NAME = ParameterValueEntityType.class
            .getAnnotation(ParameterValueRegistered.class).shortName();
    private static final String CHAT_COLOR = ParameterValueEntityType.class
            .getAnnotation(ParameterValueRegistered.class).chatColor();
    private static final int PRIORITY = ParameterValueEntityType.class.getAnnotation(ParameterValueRegistered.class)
            .priority();

    private final EntityType entityType;

    private int id;

    public ParameterValueEntityType(EntityType entityType) {
        this.entityType = entityType;
        id = ID_NON_CREATED_VALUE;
    }

    // Needed for load from database
    public static ParameterValueEntityType newInstance(String value) throws ParameterValueException {
        EntityType entityType;

        try {
            String entityTypeStr = value.toUpperCase();
            entityType = EntityType.valueOf(entityTypeStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            String msg = format(
                    "Entity type not found: Wrong name in the database or Bukkit API is changed? [entityType=%s]",
                    value);
            throw new ParameterValueException(msg, e);
        }

        return new ParameterValueEntityType(entityType);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getShortName() {
        return SHORT_NAME;
    }

    @Override
    public String getChatColor() {
        return CHAT_COLOR;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getValue() {
        return entityType.name();
    }

    @Override
    public boolean hasAccess(Entity entity) {
        return entity.getType() == entityType;
    }

    @Override
    public boolean hasAccess(Entity entity, Land originLand) {
        return hasAccess(entity);
    }

    @Override
    public String toString() {
        return "{" +
                " entityType='" + entityType + "'" +
                ", id='" + getId() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ParameterValueEntityType)) {
            return false;
        }
        ParameterValueEntityType parameterValueEntityType = (ParameterValueEntityType) o;
        return Objects.equals(entityType, parameterValueEntityType.entityType) && id == parameterValueEntityType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType, id);
    }
}
