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
package me.tabinol.secuboid.api.thread;

import java.util.Set;

import me.tabinol.secuboid.api.exceptions.SecuboidRuntimeException;

/**
 * Interface for queue processor with a type T to send and a return R if needed.
 */
public interface QueueProcessor<T, R> {

    /**
     * Process what it should execute inside the loop.
     *
     * @param t the t
     * @return the value for the return (or callback)
     */
    R process(T t);

    /**
     * Process (set) and sync what it should execute inside the loop.
     *
     * @param t the t
     * @return the value for the return (or callback)
     */
    default Set<R> processMultipleSync(T t) {
        throw new SecuboidRuntimeException("processMultiple Not implemented!");
    }
}