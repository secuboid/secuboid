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

package app.secuboid.core.players;

import app.secuboid.api.messages.MessagePath;
import app.secuboid.api.messages.MessageType;
import app.secuboid.api.players.ChatPage;
import app.secuboid.core.messages.MessagePaths;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.util.ChatPaginator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import static app.secuboid.core.messages.Message.message;

public class ChatPageImpl implements ChatPage {

    private static final String RUN_COMMAND_PAGE_PREFIX = "/sd page ";
    private static final int PAGE_WIDTH = ChatPaginator.AVERAGE_CHAT_PAGE_WIDTH;
    private static final int PAGE_HEIGHT = ChatPaginator.OPEN_CHAT_PAGE_HEIGHT - 2;

    private final @NotNull CommandSender sender;
    private final @NotNull String subject;
    private final @NotNull String text;

    private int totalPages;

    public ChatPageImpl(@NotNull CommandSender sender, @NotNull String subject, @NotNull String text) {
        this.sender = sender;
        this.subject = subject;
        this.text = text;

        totalPages = 0;
    }

    @Override
    public void show(int pageNumber) {
        ChatPaginator.ChatPage page = ChatPaginator.paginate(text, pageNumber, PAGE_WIDTH, PAGE_HEIGHT);
        totalPages = page.getTotalPages();

        if (pageNumber < 1 || pageNumber > totalPages) {
            message().sendMessage(sender, MessageType.ERROR, MessagePaths.chatPageNotFound(1, totalPages));
            return;
        }

        message().sendMessage(sender, MessageType.TITLE, MessagePaths.chatPageHeader(subject, pageNumber, totalPages));
        sender.sendMessage(page.getLines());

        if (totalPages > 1) {
            showClickableText(sender, pageNumber);
        }
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    private void showClickableText(CommandSender sender, int pageNumber) {
        List<TextComponent> textComponents = new ArrayList<>();

        if (pageNumber > 1) {
            TextComponent textComponent = addPageTextClickable(MessagePaths::chatPageFooterLeftActive, pageNumber - 1);
            textComponents.add(textComponent);
        }

        if (pageNumber < totalPages) {
            if (!textComponents.isEmpty()) {
                textComponents.add(new TextComponent(" "));
            }
            TextComponent textComponent = addPageTextClickable(MessagePaths::chatPageFooterRightActive, pageNumber + 1);
            textComponents.add(textComponent);
        }

        sender.spigot().sendMessage(textComponents.toArray(TextComponent[]::new));
    }

    private @NotNull TextComponent addPageTextClickable(IntFunction<MessagePath> messagePathFunc,
                                                        int targetPageNumber) {
        MessagePath messagePath = messagePathFunc.apply(targetPageNumber);
        TextComponent textComponent = message().getTextComponent(MessageType.CLICKABLE, messagePath);
        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                RUN_COMMAND_PAGE_PREFIX + targetPageNumber);
        textComponent.setClickEvent(clickEvent);

        return textComponent;
    }
}
