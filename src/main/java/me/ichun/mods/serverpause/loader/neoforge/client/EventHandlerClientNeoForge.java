package me.ichun.mods.serverpause.loader.neoforge.client;

import me.ichun.mods.serverpause.client.core.EventHandlerClient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class EventHandlerClientNeoForge extends EventHandlerClient
{
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event)
    {
        onClientTickEnd();
    }

    @SubscribeEvent
    public void onClientConnect(ClientPlayerNetworkEvent.LoggingIn event)
    {
        onClientServerConnectionChange();
    }

    @SubscribeEvent
    public void onClientDisconnect(ClientPlayerNetworkEvent.LoggingOut event)
    {
        onClientServerConnectionChange();
    }
}
