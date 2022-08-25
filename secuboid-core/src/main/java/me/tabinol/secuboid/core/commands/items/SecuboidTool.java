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
package me.tabinol.secuboid.core.commands.items;

import me.tabinol.secuboid.api.messages.MessageType;
import me.tabinol.secuboid.core.messages.MessagePaths;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

import static me.tabinol.secuboid.core.messages.Message.message;

public class SecuboidTool {

    private static final Material SECUBOID_TOOL_MATERIAL = Material.WOODEN_SHOVEL;
    private static final PersistentDataType<Byte, Byte> SECUBOID_TOOL_PRESISTENT_DATA_TYPE = PersistentDataType.BYTE;
    private static final String SECUBOID_TOOL_PRESISTENT_DATA_KEY_NAME = "secuboid-tool";
    private static final byte SECUBOID_TOOL_PRESISTENT_DATA_VALUE = 1;
    private static final Enchantment SECUBOID_TOOL_ENCHANTMENT = Enchantment.DURABILITY;
    private static final int SECUBOID_TOOL_ENCHANTMENT_VALUE = 1;

    private NamespacedKey toolNamespacedKey;

    public void init(@NotNull Plugin plugin) {
        toolNamespacedKey = new NamespacedKey(plugin, SECUBOID_TOOL_PRESISTENT_DATA_KEY_NAME);
    }

    public void give(@NotNull Player player) {
        if (isPlayerAsSecuboidTool(player)) {
            message().sendMessage(player, MessageType.ERROR, MessagePaths.toolAlready());
            return;
        }

        ItemStack itemStack = createTool();

        PlayerInventory playerInventory = player.getInventory();
        HashMap<Integer, ItemStack> posToItem = playerInventory.addItem(itemStack);

        if (!posToItem.isEmpty()) {
            message().sendMessage(player, MessageType.ERROR, MessagePaths.generalInventoryFull());
            return;
        }

        message().sendMessage(player, MessageType.NORMAL, MessagePaths.toolDone());
    }

    public boolean isSecuboidTool(@Nullable ItemStack itemStack) {

        if (itemStack == null) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return false;
        }

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        Byte value = persistentDataContainer.get(toolNamespacedKey, SECUBOID_TOOL_PRESISTENT_DATA_TYPE);

        if (Byte.valueOf(SECUBOID_TOOL_PRESISTENT_DATA_VALUE).equals(value)) {
            return true;
        }

        return false;
    }

    @NotNull
    private ItemStack createTool() {
        ItemStack itemStack = new ItemStack(SECUBOID_TOOL_MATERIAL);

        ItemMeta itemMeta = itemStack.getItemMeta();
        String name = message().get(MessageType.NORMAL, MessagePaths.toolName());
        itemMeta.setDisplayName(name);
        String lore1 = message().get(MessageType.NO_COLOR, MessagePaths.toolLore1());
        String lore2 = message().get(MessageType.NO_COLOR, MessagePaths.toolLore2());
        List<String> lores = List.of(lore1, lore2);
        itemMeta.setLore(lores);

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.set(toolNamespacedKey, SECUBOID_TOOL_PRESISTENT_DATA_TYPE,
                SECUBOID_TOOL_PRESISTENT_DATA_VALUE);

        itemStack.setItemMeta(itemMeta);

        itemStack.addEnchantment(SECUBOID_TOOL_ENCHANTMENT, SECUBOID_TOOL_ENCHANTMENT_VALUE);

        return itemStack;
    }

    private boolean isPlayerAsSecuboidTool(@NotNull Player player) {
        for (ItemStack itemStack : player.getInventory().all(SECUBOID_TOOL_MATERIAL).values()) {
            if (isSecuboidTool(itemStack)) {
                return true;
            }
        }

        return false;
    }
}
