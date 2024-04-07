package com.lumivoid

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

object RedstoneHelperClient : ClientModInitializer {
    private var messageSent = false

    override fun onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            val player = client.player
            if (player != null) {
                val pos = player.blockPos
                if (pos != null && GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS && GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_X) == GLFW.GLFW_PRESS && !messageSent) {
                    val coordsMessage = "Current coordinates: ${pos.x}, ${pos.y}, ${pos.z}"
                    player.sendMessage(Text.literal(coordsMessage), false)
                    messageSent = true
                } else if (GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_X) == GLFW.GLFW_RELEASE || GLFW.glfwGetKey(client.window.handle, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_RELEASE) {
                    messageSent = false
                }
            }
        })
    }
}