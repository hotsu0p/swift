package com.swift.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import org.slf4j.LoggerFactory

class CatCommand {
    private val logger = LoggerFactory.getLogger("swift")
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(
            CommandManager.literal("cat")
                .executes { context ->
                    val catAsciiArt = """
    
                                ／＞   フ
                    　　　 　   | 　_　 _|
                    　 　　 　／` ミ＿x ノ
                    　　 　 /　　　 　 |
                    　　　 /　 ヽ　　 ﾉ
                    　 　 │　　|　|　|
                    　／￣|　　 |　|　|
                    　| (￣ヽ＿_ヽ_)__)
                    　＼二つ

                    """.trimIndent()
                    context.source.sendFeedback({ Text.literal(catAsciiArt) }, false)
                    1
                }
        )
    }
}
