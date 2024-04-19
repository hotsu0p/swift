
package com.swift.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.slf4j.LoggerFactory

class CctPosCommand {
    private val logger = LoggerFactory.getLogger("swift")

    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal("cctpos")
                .executes { context ->
                    try {
                        val player = context.source.player
                        val world = player?.world
                        val pos = player?.blockPos
                        if (player != null && world != null && pos != null) {
                            val adjustedX = pos.x
                            val adjustedY = pos.y 
                            val adjustedZ = pos.z 
                            
                            val coordsText = "/tp @s $adjustedX $adjustedY $adjustedZ"
                            val message = {
                                Text.literal("")
                                    .append(Text.literal(coordsText).styled { style ->
                                        style.withColor(Formatting.BLUE)
                                            .withClickEvent(net.minecraft.text.ClickEvent(net.minecraft.text.ClickEvent.Action.COPY_TO_CLIPBOARD, coordsText))
                                            .withHoverEvent(net.minecraft.text.HoverEvent(net.minecraft.text.HoverEvent.Action.SHOW_TEXT, Text.literal("Click to copy coordinates")))
                                    })
                            }
                            context.source.sendFeedback(message, false)
                        } else {
                            context.source.sendFeedback({ Text.literal("Player or world not found.") }, false)
                        }
                    } catch (error: Exception) {
                        context.source.sendFeedback({ Text.literal("Error: ${error.message}") }, false)
                        logger.error(error.toString())
                    }
                    1 
                }
        )
    }
}
