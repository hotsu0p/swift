package com.lumivoid

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.text.Text

object SwiftHelperClient : ClientModInitializer {
    private var messageSent = false

    override fun onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            if (client.player != null && client.options.forwardKey.isPressed) {
                if (!messageSent) {
                    client.player?.sendMessage(Text.literal("W key is pressed"), false)
                    messageSent = true
                }
            } else {
                messageSent = false
            }
        })
    }
}
